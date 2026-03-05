import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function AdminDashboard() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = () => {
    API.get("/products")
      .then((res) => setProducts(res.data))
      .catch((err) => console.error(err));
  };

  const deleteProduct = (id) => {
    API.delete(`/products/${id}`)
      .then(() => loadProducts())
      .catch((err) => {
        console.error(err);
        alert("Failed to delete product");
      });
  };

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="mb-0">Admin Dashboard</h2>
        <div>
          <button className="btn btn-primary me-2" onClick={() => navigate("/add-product")}>
            Add Product
          </button>
          <button className="btn btn-danger" onClick={logout}>
            Logout
          </button>
        </div>
      </div>

      <div className="table-responsive">
        <table className="table table-bordered table-striped align-middle">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Image</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {products.map((p) => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.name}</td>
                <td>Rs {p.price}</td>
                <td>{p.quantity}</td>
                <td>
                  {p.imageUrl ? (
                    <img src={p.imageUrl} alt={p.name} style={{ width: "70px", height: "50px", objectFit: "cover" }} />
                  ) : (
                    "No Image"
                  )}
                </td>
                <td>
                  <button className="btn btn-warning btn-sm me-2" onClick={() => navigate(`/update-product/${p.id}`)}>
                    Edit
                  </button>
                  <button className="btn btn-danger btn-sm" onClick={() => deleteProduct(p.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AdminDashboard;
