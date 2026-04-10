import Navbar from "./Navbar";

export default function Home() {
  return (
    <>
      <Navbar />
      <main className="content-wrap">
        <section className="card">
          <h1>Home</h1>
          <p>You are logged in. Use the Profile page to view complete user information.</p>
        </section>
      </main>
    </>
  );
}
