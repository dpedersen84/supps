import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Button } from "reactstrap";
import "./OrderComplete.css";

class OrderComplete extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      userId: "",
      order: {},
      products: [],
      isLoading: true
    };
  }
  render() {
    return (
      <div className="orderComplete mt-5">
        <div className="container">
          <h1>Thanks!</h1>
          <div className="row">
            <div className="col text-center">
              <Button
                size="lg"
                outline
                color="primary"
                tag={Link}
                to="/products"
              >
                Keep Shopping
              </Button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default OrderComplete;
