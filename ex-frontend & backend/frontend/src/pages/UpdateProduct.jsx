import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import API from "../services/api";

function UpdateProduct() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    name: "",
    price: "",
    quantity: "",
    imageUrl: ""
  });

  useEffect(() => {
    loadProduct();
  }, []);

  const loadProduct = () => {
    API.get(`/products/${id}`)
      .then((res) => {
        setProduct(res.data);
      })
      .catch((err) => {
        console.error("Error loading product:", err);
      });
  };

  const handleChange = (e) => {
    setProduct({
      ...product,
      [e.target.name]: e.target.value
    });
  };

  const updateProduct = () => {
    API.put(`/products/${id}`, product)
      .then(() => {
        alert("Product updated successfully");
        navigate("/admin-dashboard");
      })
      .catch((err) => {
        console.error("Update failed:", err);
      });
  };

  return (
    <div className="container mt-4" style={{ maxWidth: "650px" }}>
      <h2>Update Product</h2>

      <input
        type="text"
        name="name"
        value={product.name}
        placeholder="Product Name"
        className="form-control mb-2"
        onChange={handleChange}
      />

      <input
        type="number"
        name="price"
        value={product.price}
        placeholder="Price"
        className="form-control mb-2"
        onChange={handleChange}
      />

      <input
        type="number"
        name="quantity"
        value={product.quantity}
        placeholder="Quantity"
        className="form-control mb-2"
        onChange={handleChange}
      />

      <input
        type="text"
        name="imageUrl"
        value={product.imageUrl}
        placeholder="Image URL"
        className="form-control mb-2"
        onChange={handleChange}
      />

      {product.imageUrl && (
        <img
          src={product.imageUrl}
          alt="Product"
          width="150"
          className="mb-3"
        />
      )}

      <br />

      <button className="btn btn-success" onClick={updateProduct}>
        Update Product
      </button>
      <button className="btn btn-secondary ms-2" onClick={() => navigate("/admin-dashboard")}>
        Cancel
      </button>
    </div>
  );
}

export default UpdateProduct;
