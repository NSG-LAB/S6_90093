import { useAuth } from '../context/AuthContext.jsx'

const Home = () => {
  const { identity } = useAuth()

  return (
    <section className="card-grid">
      <article className="info-card">
        <p className="eyebrow">Dashboard</p>
        <h1>Welcome back, {identity?.username}!</h1>
        <p>
          You are now signed in. Use the navigation to update your profile or review your stored
          information. This page only renders when your session key exists in storage.
        </p>
      </article>
      <article className="info-card subtle">
        <h3>Session Details</h3>
        <ul>
          <li>Storage: localStorage</li>
          <li>Stored keys: user id & username</li>
          <li>Next stop: Profile to load full record</li>
        </ul>
      </article>
    </section>
  )
}

export default Home
