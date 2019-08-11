import React, { Component } from "react";
import "./Order.css";

class Order extends Component {
  constructor(props) {
    super(props);
    this.state = {
      products: []
    };
  }
  componentDidMount() {
    console.log(localStorage);

    const items = { ...localStorage };
    console.log(items);

    // var products = items.forEach(function(i) {
    //   JSON.parse(i.value);
    // });

    // console.log(products);
  }
  render() {
    return (
      <div>
        <h1>Order</h1>
      </div>
    );
  }
}

export default Order;
