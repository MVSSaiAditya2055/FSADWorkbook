import { Link, useNavigate } from "react-router-dom";
import { clearStoredUser, getStoredUser } from "../services/authStorage";

export default function Navbar() {
  const navigate = useNavigate();
  const user = getStoredUser();

  const handleLogout = () => {
    clearStoredUser();
    navigate("/login");
  };

  return (
    <header className="navbar">
      <div className="brand">Auth App</div>
      <nav className="nav-links">
        <Link to="/home">Home</Link>
        <Link to="/profile">Profile</Link>
        <button type="button" onClick={handleLogout} className="logout-btn">
          Logout
        </button>
      </nav>
      <div className="welcome">{user ? `Hi, ${user.username}` : ""}</div>
    </header>
  );
}
