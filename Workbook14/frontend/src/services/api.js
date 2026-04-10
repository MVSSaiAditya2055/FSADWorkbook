const API_BASE_URL = "http://localhost:8080/api";

export async function registerUser(payload) {
  const response = await fetch(`${API_BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || "Registration failed");
  }

  return response.json();
}

export async function loginUser(payload) {
  const response = await fetch(`${API_BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || "Login failed");
  }

  return response.json();
}

export async function getUserProfile(user) {
  const params = new URLSearchParams();

  if (user?.userId) {
    params.append("userId", user.userId);
  } else if (user?.username) {
    params.append("username", user.username);
  } else {
    throw new Error("No logged in user found");
  }

  const response = await fetch(`${API_BASE_URL}/users/profile?${params.toString()}`);

  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || "Profile fetch failed");
  }

  return response.json();
}
