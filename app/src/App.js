import React, { Component } from "react";

class App extends Component {
  state = {
    isLoading: true,
    products: []
  };

  async componentDidMount() {
    const response = await fetch("/api/products");
    const body = await response.json();
    this.setState({ products: body, isLoading: false });
    console.log(this.state.products);
  }

  render() {
    const { products, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <div className="products">
          <h1>Products</h1>
          {products.map(p => (
            <div key={p.id}>{p.name}</div>
          ))}
        </div>
      </div>
    );
  }
}

export default App;
