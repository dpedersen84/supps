import React, { Component } from "react";
import {
  Button,
  Spinner,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Table
} from "reactstrap";
import { Link, withRouter } from "react-router-dom";
import axios from "axios";
import "./Checkout.css";

class Checkout extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      userId: 4,
      products: [],
      order: {},
      totalPrice: "",
      isLoading: true
    };
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    axios
      .get(`/api/orders/unsent/${this.state.userId}`)
      .then(response => {
        if (this._isMounted) {
          this.setState({
            order: response.data,
            totalPice: response.data.totalPrice,
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

  deleteFromOrder(orderId, productId) {
    axios
      .delete(`/api/orders/${orderId}/${productId}`)
      .then(() => {
        let updatedProducts = [...this.state.products].filter(
          p => p.productId !== productId
        );
        this.setState({ products: updatedProducts });
      })
      .then(() => {
        axios
          .get(`/api/orders/unsent/${this.state.userId}`)
          .then(response => {
            if (this._isMounted) {
              this.setState({
                order: response.data,
                totalPice: response.data.totalPrice,
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
      });
  }

  async handleOrderSubmit() {
    const { order } = this.state;

    axios.post("/api/orders/sent", order).then(response => {
      this.props.history.push("/complete");
    });
  }

  render() {
    const { isLoading, products, order } = this.state;

    if (isLoading) {
      return (
        <div className="checkout mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    if (products.length === 0) {
      return (
        <div className="checkout mt-5">
          <div className="container">
            <h3>You have not added any products!</h3>
            <div className="row">
              <div className="col text-center">
                <Button
                  size="lg"
                  outline
                  color="primary"
                  tag={Link}
                  to="/products"
                >
                  View Products
                </Button>
              </div>
            </div>
          </div>
        </div>
      );
    }

    const productData = products.map(product => {
      return (
        <tr key={product.productId}>
          <td>
            <Link to={`/products/${product.productId}`}>{product.name}</Link>
          </td>
          <td>${product.price}.00</td>
          <td>
            <Button
              size="sm"
              outline
              color="danger"
              onClick={this.deleteFromOrder.bind(
                this,
                order.orderId,
                product.productId
              )}
            >
              Remove
            </Button>
          </td>
        </tr>
      );
    });

    return (
      <div className="checkout mt-5">
        <div className="container">
          <Card>
            <CardHeader tag="h3">Checkout</CardHeader>
            <CardBody>
              <Table>
                <thead>
                  <tr>
                    <th width="30%">Product</th>
                    <th width="15%">Price</th>
                    <th width="15%" />
                  </tr>
                </thead>
                <tbody>{productData}</tbody>
              </Table>
            </CardBody>
            <CardFooter className="text-right">
              <strong>Order Total: ${order.totalPrice}.00</strong>
              <Button
                tag={Link}
                to="/products"
                outline
                color="info"
                className="ml-2"
              >
                Keep Shopping
              </Button>
              <Button
                color="success"
                className="ml-2"
                onClick={() => this.handleOrderSubmit()}
              >
                Submit
              </Button>
            </CardFooter>
          </Card>
        </div>
      </div>
    );
  }
}

export default withRouter(Checkout);
