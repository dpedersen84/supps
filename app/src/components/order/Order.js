import React, { Component } from "react";
import { Button, Spinner, Table } from "reactstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Order.css";

class Order extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      order: {},
      products: [],
      isLoading: true
    };
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    axios
      .get(`/api/orders/${this.props.match.params.id}`)
      .then(result => {
        if (this._isMounted) {
          this.setState({
            order: result.data,
            isLoading: false
          });

          if (result.data.products != null) {
            this.setState({
              products: result.data.products
            });
          }
        }
      })
      .catch(error => {
        console.log(error);
      });
  }

  render() {
    const { isLoading, order, products } = this.state;
    let productData;

    if (isLoading) {
      return (
        <div className="order mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    if (products != null) {
      productData = products.map(prod => {
        return (
          <tr key={prod.productId}>
            <td>{prod.productId}</td>
            <td>
              <Link to={`/products/${prod.productId}`}>{prod.name}</Link>
            </td>
            <td>${prod.price}.00</td>
            <td>{prod.inventory}</td>
          </tr>
        );
      });
    }

    if (products.length === 0) {
      productData = (
        <tr>
          <td>No products in order.</td>
        </tr>
      );
    }

    return (
      <div className="order mt-5">
        <div className="container">
          <Button tag={Link} to="/admin">
            Back To Admin
          </Button>
          <div className="row mt-4" id="orderTable">
            <div className="col-md-12">
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="25%">Id</th>
                    <th width="25%">UserId</th>
                    <th width="25%">Total Price</th>
                    <th width="25%">Order Date</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>{order.orderId}</td>
                    <td>{order.userId}</td>
                    <td>${order.totalPrice}.00</td>
                    <td>
                      {new Intl.DateTimeFormat("en-US", {
                        year: "numeric",
                        month: "long",
                        day: "2-digit"
                      }).format(new Date(order.orderDate))}
                    </td>
                  </tr>
                </tbody>
              </Table>
            </div>
          </div>
          <div className="row mt-4" id="productTable">
            <div className="col-md-12">
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">Name</th>
                    <th width="10%">Price</th>
                    <th width="10%">Inventory</th>
                  </tr>
                </thead>
                <tbody>{productData}</tbody>
              </Table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Order;
