import './App.css';
import NavbarComponent from './components/NavbarComponent';
import Home from './pages/Home';

import 'bootstrap-icons/font/bootstrap-icons.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Footer from './components/Footer';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Router>
        <NavbarComponent />
        <Home />
      </Router>
      <Footer />
    </div>
  );
}

export default App;
