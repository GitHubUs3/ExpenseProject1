import './App.css';
import { ListForm } from './components/Expense/ListForm';
import { ExpenseForm } from './components/Expense/NewForm';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { NavBar } from './components/NavBar';
import { Link } from 'react-router-dom';
import { Container, Card} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'; 

function App() {
  return (
    <BrowserRouter>
    <Card>
    <NavBar>
      
    <section className='nav-section'>
      <div className='nav-item'>
        <Link className='nav-item' to="/">Forms</Link>
      </div>
      <div className='nav-item'>
        <Link className='nav-item' to="/addForm">Reimbursement Form</Link>
      </div>
    </section>
    
    </NavBar>
    </Card>
      <Routes>
        <Route path="/" element={<ListForm/>}/>
        <Route path="/addForm" element={<ExpenseForm/>}/>

      </Routes>
    </BrowserRouter>
  );
}

export default App;
