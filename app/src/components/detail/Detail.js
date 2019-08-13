import React, { Component } from "react";
import { Button, Spinner } from "reactstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Detail.css";

class Detail extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      userId: 4,
      order: {},
      product: {},
      products: [],
      goal: {},
      category: {},
      reviews: [],
      isLoading: true
    };
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    axios
      .get(`/api/products/${this.props.match.params.id}`)
      .then(response => {
        if (this._isMounted) {
          this.setState({
            product: response.data,
            goal: response.data.goal,
            category: response.data.category
          });
        }
      })
      .catch(error => {
        console.log(error);
      });

    axios
      .get(`/api/reviews/product/${this.props.match.params.id}`)
      .then(response => {
        if (this._isMounted) {
          this.setState({
            reviews: response.data
          });
        }
      })
      .catch(error => {
        console.log(error);
      });

    axios
      .get(`/api/orders/unsent/${this.state.userId}`)
      .then(response => {
        if (this._isMounted) {
          this.setState({
            order: response.data,
            isLoading: false
          });

          if (response.data.products != null) {
            this.setState({
              products: response.data.products
            });
          }
        }
      })
      .catch(error => {
        console.log(error);
      });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  addToOrder(orderId, productId) {
    axios.post(`/api/orders/${orderId}/${productId}`).then(() => {
      axios
        .get(`/api/orders/unsent/${this.state.userId}`)
        .then(response => {
          if (this._isMounted) {
            this.setState({
              order: response.data
            });

            if (response.data.products != null) {
              this.setState({
                products: response.data.products
              });
            }
          }
        })
        .catch(error => {
          console.log(error);
        });
    });
  }

  deleteFromOrder(orderId, productId) {
    axios.delete(`/api/orders/${orderId}/${productId}`).then(() => {
      let updatedProducts = [...this.state.products].filter(
        p => p.productId !== productId
      );
      this.setState({ products: updatedProducts });
    });
  }

  render() {
    const {
      product,
      reviews,
      goal,
      category,
      isLoading,
      order,
      products
    } = this.state;

    if (isLoading) {
      return (
        <div className="details mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    // This button will render if no products are in state
    const buttonPrimary = (
      <div className="col">
        <Button
          outline
          color="primary"
          onClick={this.addToOrder.bind(this, order.orderId, product.productId)}
          size="lg"
          block
        >
          Add To Order
        </Button>
      </div>
    );

    // If products are in state, this button will render, dynamically updating on add or remove
    const buttonSecondary = (
      <div className="col">
        {products.some(prod => prod.productId === product.productId) ? (
          <Button
            outline
            color="warning"
            onClick={this.deleteFromOrder.bind(
              this,
              order.orderId,
              product.productId
            )}
            size="lg"
            block
          >
            Remove From Order
          </Button>
        ) : (
          <Button
            outline
            color="primary"
            onClick={this.addToOrder.bind(
              this,
              order.orderId,
              product.productId
            )}
            size="lg"
            block
          >
            Add To Order
          </Button>
        )}
      </div>
    );

    return (
      <div className="details mt-5">
        <div className="container">
          <Button tag={Link} to="/products">
            Back To Products
          </Button>
          <div className="row mt-4">
            <div className="col-md-4">
              <img src={product.image} alt={product.image} className="mb-3" />
              <h5>Product Overview</h5>
              <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Repudiandae nisi accusamus pariatur dicta adipisci saepe atque
                necessitatibus molestiae ratione asperiores minima ducimus in
                corrupti tempora nostrum possimus consectetur, vero corporis.
              </p>
            </div>
            <div className="col-md-8">
              <div className="row">
                {" "}
                <h2 className="ml-3">{product.name}</h2>
              </div>
              <div className="row">
                <p className="ml-3">{product.headline}</p>
              </div>
              <hr />
              <div className="row">
                <h5 className="ml-3">${product.price}</h5>
              </div>
              <hr />
              <div className="row">
                <div className="col">
                  <Button outline color="success" disabled>
                    {goal.name}
                  </Button>{" "}
                </div>
                <div className="col">
                  <Button outline color="info" disabled>
                    {category.name}
                  </Button>{" "}
                </div>
              </div>
              <div className="row mt-5">
                {products != null ? buttonSecondary : buttonPrimary}
              </div>
            </div>
          </div>
          <hr />
          <div className="row mt-4">
            <div className="col">
              <h3>Reviews</h3>
            </div>
            <div className="col-right">
              <Button
                outline
                color="warning"
                tag={Link}
                to={`/add/review/${product.productId}`}
              >
                Add Review
              </Button>
            </div>
          </div>
          {reviews.map(r => (
            <div key={r.id}>
              <div className="row mt-3 ml-2">
                <h5>{r.rating} Stars!</h5>
              </div>
              <div className="row mt-3 ml-2">
                <p>{r.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default Detail;
