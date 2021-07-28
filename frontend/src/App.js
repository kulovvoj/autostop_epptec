import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'
import LoginPage from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import CarListPage from './pages/CarListPage';
import RideListPage from './pages/RideListPage';
import MainPage from './pages/MainPage';
import Navbar from './components/navbar/Navbar'

function App() {

  
  return (
    <Router>
      <div>
        <Navbar />
        <Switch>
          <Route path="/" exact component={MainPage} />
          <Route path="/login" component={LoginPage} />
          <Route path="/profile" component={ProfilePage} />
          <Route path="/cars" component={CarListPage} />
          <Route path="/rides" component={RideListPage} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
