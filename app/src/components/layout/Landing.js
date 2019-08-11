import React, { Component } from "react";
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
                {/* <Link className="btn btn-lg btn-primary mr-2" to="/register">
                  Register
                </Link>
                <Link className="btn btn-lg btn-primary" to="/login">
                  Login
                </Link> */}
                <Link className="btn btn-lg btn-primary" to="/products">
                  Get Started
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Landing;
