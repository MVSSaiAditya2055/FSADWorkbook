import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { getUserProfile } from "../services/api";
import { getStoredUser } from "../services/authStorage";
import Navbar from "./Navbar";

export default function Profile() {
  const storedUser = getStoredUser();
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadProfile() {
      try {
        const data = await getUserProfile(storedUser);
        setProfile(data);
      } catch (err) {
        setError(err.message || "Unable to load profile");
      } finally {
        setLoading(false);
      }
    }

    if (storedUser) {
      loadProfile();
    }
  }, [storedUser]);

  if (!storedUser) {
    return <Navigate to="/login" replace />;
  }

  return (
    <>
      <Navbar />
      <main className="content-wrap">
        <section className="card">
          <h1>Profile</h1>
          {loading && <p>Loading profile...</p>}
          {error && <p className="error">{error}</p>}
          {profile && (
            <div className="profile-grid">
              <div>
                <strong>User ID:</strong> {profile.id}
              </div>
              <div>
                <strong>Username:</strong> {profile.username}
              </div>
              <div>
                <strong>Full Name:</strong> {profile.fullName}
              </div>
              <div>
                <strong>Email:</strong> {profile.email}
              </div>
            </div>
          )}
        </section>
      </main>
    </>
  );
}
