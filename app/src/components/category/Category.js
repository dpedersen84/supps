import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import axios from "axios";
import "./Category.css";

class Category extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ""
    };
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleInputChange = event => {
    const { name, value } = event.target;

    this.setState({
      [name]: value
    });
  };

  async handleSubmit(event) {
    event.preventDefault();

    const { name } = this.state;

    const newCategory = {
      name: name
    };

    axios
      .post("/api/category", newCategory)
      .then(res => {
        this.props.history.push("/admin");
      })
      .catch(error => {
        console.log(error);
      });
  }

  render() {
    return (
      <div className="categories mt-5">
        <div className="container">
          <h1>Add A Category</h1>
          <div className="row">
            <div className="col-md-3">
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="name">Name</Label>
                  <Input
                    type="text"
                    id="name"
                    name="name"
                    value={this.state.name}
                    onChange={this.handleInputChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Button
                    outline
                    color="primary"
                    type="submit"
                    disabled={!this.state.name}
                  >
                    Save
                  </Button>
                  <Button tag={Link} to="/admin" className="ml-2">
                    Cancel
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

export default withRouter(Category);
