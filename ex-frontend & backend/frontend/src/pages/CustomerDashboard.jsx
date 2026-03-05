import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function CustomerDashboard() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = () => {
    API.get("/products")
      .then((res) => {
        setProducts(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  const buyProduct = () => {
    alert("Buy feature will be implemented next");
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="mb-0">Customer Dashboard</h2>
        <button className="btn btn-danger" onClick={logout}>
          Logout
        </button>
      </div>

      <div className="row">
        {products.map((p) => (
          <div className="col-md-3" key={p.id}>
            <div className="card mb-4 shadow-sm">
              <img
                src={p.imageUrl}
                alt={p.name}
                className="card-img-top"
                style={{ height: "200px", objectFit: "cover" }}
              />

              <div className="card-body">
                <h5 className="card-title">{p.name}</h5>
                <p className="card-text">Price: Rs {p.price}</p>
                <p className="card-text">Available: {p.quantity}</p>

                <button
                  className="btn btn-success"
                  onClick={() => buyProduct(p.id)}
                  disabled={p.quantity === 0}
                >
                  {p.quantity === 0 ? "Out of Stock" : "Buy"}
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CustomerDashboard;
