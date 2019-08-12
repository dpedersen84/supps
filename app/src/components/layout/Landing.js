import React, { Component } from "react";
import { Button } from "reactstrap";
import { Link } from "react-router-dom";
import "./Landing.css";

class Landing extends Component {
  render() {
    return (
      <div className="landing">
        <div className="landing-inner text-light">
          <div className="container">
            <div className="row mt-5">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4 supps">Supps</h1>
                <p className="lead">
                  {" "}
                  Find the best supplements for your health and fitness!
                </p>
                <hr />
                <Button color="primary" tag={Link} to="/products">
                  Get Started
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Landing;
