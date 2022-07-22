import axios from 'axios';
import { useState, useEffect } from 'react';
import { ListItem } from './ListItem';
import {Button, Container, Table, Card} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
/* This is intended to display a table of your reimbursment forms 
and have buttons to approve, deny , or delete a row.
*/

export const ListForm = () => {
    
    const [emp, setEmp] = useState([]);
    const [update, setUpdate] = useState(false);

    // retrieves table
    useEffect(() => {
        axios.get(`http://localhost:8080/expense-reimburse-project/list-form`)
        .then((response) => {console.log(response.data) 
            setEmp(response.data)})
        .catch((err) => {console.log(err)})
    }, []) // every time update is changed -> useEffect hook is called again

const removeItem = (id) => {
    //update expense
    setEmp(
        emp.filter((item) => {
  
          return item.id !== id; //return items that don't have the id passed in
  
        }))
}
    return (
        <>
        <Container classname="text-center">
        <Card>
        <Card.Body>
        <Table striped bordered hover size="sm" variant='light' responsive>
            <thead>
                <tr>
                    <td>Name</td>
                    <td>Reason</td>
                    <td>Notes</td>
                    <td>Status</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                {emp.map((e) =>(
                    <ListItem e={e} removeItem={removeItem} setUpdate={setUpdate} update={update} setEmp={setEmp} key={e.expenseId}/> 
                    
                ))}

            </tbody>
        </Table>
        </Card.Body>
        </Card>
        </Container>
        </>
    );
}