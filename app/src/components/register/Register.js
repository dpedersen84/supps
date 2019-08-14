import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import axios from "axios";
import "./Register.css";

class Register extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      confirmPassword: ""
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this._isMounted = true;
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  async handleSubmit(event) {
    event.preventDefault();

    const { username, password, email } = this.state;

    const newUser = {
      username: username,
      email: email,
      password: password
    };

    axios
      .post("/api/users", newUser)
      .then(response => {
        this.props.history.push("/login");
      })
      .catch(error => console.log(error));
  }

  render() {
    return (
      <div className="register mt-5">
        <div className="container">
          <h1>Register</h1>
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
                  <Label for="confirmPassword">Confirm Password</Label>
                  <Input
                    type="password"
                    name="confirmPassword"
                    id="confirmPassword"
                    value={this.state.confirmPassword}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Button
                    outline
                    color="primary"
                    type="submit"
                    // need better validation
                    disabled={
                      !this.state.username ||
                      !this.state.password ||
                      !this.state.confirmPassword ||
                      this.state.password !== this.state.confirmPassword
                    }
                  >
                    Register
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

export default withRouter(Register);
