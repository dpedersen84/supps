import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Form, FormGroup, Label, Input, Spinner } from "reactstrap";
import axios from "axios";
import "./Login.css";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      isLoading: true
    };
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  render() {
    return (
      <div className="login mt-5">
        <div className="container">
          <h1>Login</h1>
          <div className="row">
            <div className="col-sm-12 col-md-6 offset-md-3">
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="username">Username</Label>
                  <Input
                    type="text"
                    id="username"
                    name="username"
                    value={this.state.username}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="password">Password</Label>
                  <Input
                    type="password"
                    id="password"
                    name="password"
                    value={this.state.password}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Button
                    outline
                    color="primary"
                    type="submit"
                    // need better validation
                    disabled={!this.state.username || !this.state.password}
                  >
                    Log In
                  </Button>
                </FormGroup>
              </Form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
