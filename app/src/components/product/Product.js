import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Form, FormGroup, Label, Input, Spinner } from "reactstrap";
import axios from "axios";
import "./Product.css";

class Product extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      product: {},
      goals: [],
      categories: [],
      productId: "",
      name: "",
      price: "",
      inventory: "",
      category: "",
      goal: "",
      image: "",
      headline: "",
      isLoading: true
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    fetch("/api/goals")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            goals: data
          });
        }
      });

    fetch("/api/category")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            categories: data
          });
        }
      });

    if (this.props.match.params.id) {
      fetch(`/api/products/${this.props.match.params.id}`)
        .then(res => res.json())
        .then(data => {
          if (this._isMounted) {
            this.setState({
              product: data,
              productId: data.productId,
              name: data.name,
              price: data.price,
              inventory: data.inventory,
              category: data.category.id,
              goal: data.goal.id,
              image: data.image,
              headline: data.headline,
              isLoading: false
            });
          }
        });
    } else {
      this.setState({
        isLoading: false
      });
    }
  }

  handleChange = event => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  async handleSubmit(event) {
    event.preventDefault();
    const {
      name,
      price,
      category,
      goal,
      image,
      headline,
      inventory,
      productId
    } = this.state;

    if (productId) {
      const productData = {
        productId: productId,
        name: name,
        price: parseInt(price),
        category: {
          id: parseInt(category)
        },
        goal: {
          id: parseInt(goal)
        },
        image: image,
        headline: headline,
        inventory: parseInt(inventory)
      };

      axios
        .put(`/api/products/${productId}`, productData)
        .then(res => {
          this.props.history.push("/admin");
        })
        .catch(error => {
          console.log(error);
        });
    } else {
      const newProduct = {
        name: name,
        price: parseInt(price),
        category: {
          id: parseInt(category)
        },
        goal: {
          id: parseInt(goal)
        },
        image: image,
        headline: headline,
        inventory: parseInt(inventory)
      };

      axios
        .post("/api/products", newProduct)
        .then(res => {
          this.props.history.push("/admin");
        })
        .catch(error => {
          console.log(error);
        });
    }
  }

  render() {
    const {
      goals,
      categories,
      productId,
      goal,
      category,
      isLoading
    } = this.state;

    if (isLoading) {
      return (
        <div className="product mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    return (
      <div className="product mt-5">
        <div className="container">
          {productId ? <h1>Edit Product</h1> : <h1>Add A Product</h1>}
          <div className="row">
            <div className="col-sm-12 col-md-6 offset-md-3">
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="name">Name</Label>
                  <Input
                    type="text"
                    id="name"
                    name="name"
                    value={this.state.name}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="price">Price</Label>
                  <Input
                    type="number"
                    id="price"
                    name="price"
                    value={this.state.price}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="inventory">Inventory</Label>
                  <Input
                    type="number"
                    id="inventory"
                    name="inventory"
                    value={this.state.inventory}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="goal">Goal</Label>
                  <Input
                    type="select"
                    defaultValue={goal ? goal : 1}
                    name="goal"
                    id="goal"
                    onChange={this.handleChange}
                  >
                    {goals.map(g => (
                      <option key={g.id} value={g.id}>
                        {g.name}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
                <FormGroup>
                  <Label for="category">Category</Label>
                  <Input
                    type="select"
                    defaultValue={category ? category : 1}
                    name="category"
                    id="category"
                    onChange={this.handleChange}
                  >
                    {categories.map(c => (
                      <option key={c.id} value={c.id}>
                        {c.name}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
                <FormGroup>
                  <Label for="headline">Headline</Label>
                  <Input
                    type="text"
                    id="headline"
                    name="headline"
                    value={this.state.headline}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="image">Image</Label>
                  <Input
                    type="text"
                    id="image"
                    name="image"
                    value={this.state.image}
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
                      !this.state.name ||
                      !this.state.category ||
                      !this.state.image ||
                      !this.state.goal ||
                      !this.state.inventory ||
                      !this.state.headline ||
                      !this.state.price
                    }
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

export default withRouter(Product);
