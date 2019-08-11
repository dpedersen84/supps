import React, { Component } from "react";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import "./Category.css";

class Category extends Component {
  constructor(props) {
    super(props);
    this.state = {
      categories: [],
      name: "",
      isLoading: true
    };
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this.setState({
      isLoading: true
    });

    fetch("/api/category")
      .then(res => res.json())
      .then(data =>
        this.setState({
          categories: data,
          isLoading: false
        })
      );
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

    console.log(JSON.stringify(name));

    await fetch("/api/category", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(name)
    });
    this.props.history.push("/categories");
  }

  render() {
    const { categories, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="categories mt-5">
        <div className="container">
          <h1>Categories</h1>
          <div className="row mt-4">
            {categories.map(category => (
              <div className="col-md-3" key={category.id}>
                <Button className="ml-3 mt-5">{category.name}</Button>
              </div>
            ))}
          </div>
          <div className="row mt-5">
            <h5 className="ml-3 mt-5">Add A Category</h5>
          </div>
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
                </FormGroup>
              </Form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Category;
