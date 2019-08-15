import React, { Component } from "react";
import { Alert, Button, Table, Spinner } from "reactstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Admin.css";

class Admin extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      products: [],
      orders: [],
      goals: [],
      categories: [],
      users: [],
      isLoading: true,
      visible: false,
      errorMsg: ""
    };
    this.onDismiss = this.onDismiss.bind(this);
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    fetch("/api/goals")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            goals: data
          });
        }
      });

    fetch("/api/category")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            categories: data
          });
        }
      });

    fetch("/api/products")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data
          });
        }
      });

    fetch("/api/orders/sent")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            orders: data,
            isLoading: false
          });
        }
      });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  removeProduct(productId) {
    axios
      .delete(`/api/products/${productId}`)
      .then(() => {
        let updatedProducts = [...this.state.products].filter(
          p => p.productId !== productId
        );
        this.setState({ products: updatedProducts });
      })
      .catch(error => {
        console.log(error);
        this.setState({
          errorMsg: "Delete failed!",
          visible: true
        });
      });
  }

  removeGoal(id) {
    axios
      .delete(`/api/goals/${id}`)
      .then(() => {
        let updatedGoals = [...this.state.goals].filter(g => g.id !== id);
        this.setState({ goals: updatedGoals });
      })
      .catch(error => {
        console.log(error);
        this.setState({
          errorMsg: "Delete failed! A product is still using that goal!",
          visible: true
        });
      });
  }

  removeCategory(id) {
    axios
      .delete(`/api/category/${id}`)
      .then(() => {
        let updatedCats = [...this.state.categories].filter(c => c.id !== id);
        this.setState({ categories: updatedCats });
      })
      .catch(error => {
        console.log(error);
        this.setState({
          errorMsg: "Delete failed! A product is still using that category!",
          visible: true
        });
      });
  }

  onDismiss() {
    this.setState({ visible: false });
  }

  render() {
    const { products, goals, categories, isLoading, orders } = this.state;

    if (isLoading) {
      return (
        <div className="admin mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    const productData = products.map(product => {
      return (
        <tr key={product.productId}>
          <td>{product.productId}</td>
          <td>{product.name}</td>
          <td>${product.price}.00</td>
          <td>{product.inventory}</td>
          <td>
            <Button
              size="sm"
              outline
              color="primary"
              tag={Link}
              to={`/edit/product/${product.productId}`}
            >
              Edit
            </Button>
            <Button
              size="sm"
              outline
              color="danger"
              className="ml-1"
              onClick={() => this.removeProduct(product.productId)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    const orderData = orders.map(order => {
      return (
        <tr key={order.orderId}>
          <td>{order.orderId}</td>
          <td>{order.userId}</td>
          <td>
            {new Intl.DateTimeFormat("en-US", {
              year: "numeric",
              month: "long",
              day: "2-digit"
            }).format(new Date(order.orderDate))}
          </td>
          <td>
            <Button
              size="sm"
              outline
              color="info"
              tag={Link}
              to={`/order/${order.orderId}`}
            >
              View
            </Button>
          </td>
        </tr>
      );
    });

    const goalData = goals.map(g => {
      return (
        <tr key={g.id}>
          <td>{g.id}</td>
          <td>{g.name}</td>
          <td>
            <Button
              size="sm"
              outline
              color="danger"
              onClick={() => this.removeGoal(g.id)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    const categoryData = categories.map(c => {
      return (
        <tr key={c.id}>
          <td>{c.id}</td>
          <td>{c.name}</td>
          <td>
            <Button
              size="sm"
              outline
              color="danger"
              onClick={() => this.removeCategory(c.id)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    return (
      <div className="admin mt-5">
        <div className="container">
          <h1>Admin Tools</h1>
          <Alert
            color="danger"
            isOpen={this.state.visible}
            toggle={this.onDismiss}
            className="mt-3"
          >
            {this.state.errorMsg}
          </Alert>
          <hr />
          <div className="productTable">
            <div className="float-right">
              <Button outline color="success" tag={Link} to="/add/product">
                Add Product
              </Button>
            </div>
            <h3>Products</h3>
            <Table striped bordered className="mt-4">
              <thead>
                <tr>
                  <th width="10%">Id</th>
                  <th width="15%">Name</th>
                  <th width="10%">Price</th>
                  <th width="10%">Inventory</th>
                  <th width="20%">Actions</th>
                </tr>
              </thead>
              <tbody>{productData}</tbody>
            </Table>
          </div>
          <hr />
          <div className="orderTable">
            <h3>Complete Orders</h3>
            <Table striped bordered className="mt-4">
              <thead>
                <tr>
                  <th width="25%">Id</th>
                  <th width="25%">UserId</th>
                  <th width="25%">Order Date</th>
                  <th width="25%">Actions</th>
                </tr>
              </thead>
              <tbody>{orderData}</tbody>
            </Table>
          </div>
          <hr />
          <div className="row">
            <div className="col-md-6">
              <div className="float-right">
                <Button outline color="success" tag={Link} to="/add/goal">
                  Add Goal
                </Button>
              </div>
              <h3>Goals</h3>
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">Name</th>
                    <th width="15%">Actions</th>
                  </tr>
                </thead>
                <tbody>{goalData}</tbody>
              </Table>
            </div>
            <div className="col-md-6">
              <div className="float-right">
                <Button outline color="success" tag={Link} to="/add/category">
                  Add Category
                </Button>
              </div>
              <h3>Categories</h3>
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">Name</th>
                    <th width="20%">Actions</th>
                  </tr>
                </thead>
                <tbody>{categoryData}</tbody>
              </Table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Admin;
