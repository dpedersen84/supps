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
    const { isLoading, order } = this.state;

    if (isLoading) {
      return (
        <div className="order mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }
    return (
      <div className="order mt-5">
        <div className="container">
          <Button tag={Link} to="/admin">
            Back To Admin
          </Button>
          <div className="row mt-4">
            <div className="col-md-12">
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">UserId</th>
                    <th width="10%">Order Date</th>
                    <th width="10%">Complete?</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>{order.orderId}</td>
                    <td>{order.userId}</td>
                    <td>{order.orderDate}</td>
                    <td>{order.orderSent ? "True" : "False"}</td>
                  </tr>
                </tbody>
              </Table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Order;
