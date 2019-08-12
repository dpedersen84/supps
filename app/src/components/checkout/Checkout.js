import React, { Component } from "react";
import "./Checkout.css";

class Checkout extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      userId: "",
      products: [],
      order: {},
      isLoading: true
    };
  }

  componentDidMount() {
    this._isMounted = true;
  }

  render() {
    return (
      <div className="checkout mt-5">
        <div className="container">
          <h1>Checkout</h1>
        </div>
      </div>
    );
  }
}

export default Checkout;
