import axios from 'axios';
import { useRef } from 'react';
import '../../components/component.css'; 
import {Button, Container, Table, OverlayTrigger, Card} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
/* This is intended to allow you to input and submit the form
detailing the reason for reimbursment.
*/

export const ExpenseForm = () => {
    
    const nameRef = useRef();
    const reasonRef = useRef();
    const notesRef = useRef();

    // handle post requests for creating new forms
const onClick = () => {
    axios
    .post('http://localhost:8080/expense-reimburse-project/list-form',{
        name: nameRef.current.value,

        reason: reasonRef.current.value,
    
        notes: notesRef.current.value,

        status: 1
    })
    .then((response) => {
        console.log(response.data)
    });
}
    return (
        <>
        <Container classname="text-center">
        <Card>
            <Card.Body>
            <Card.Title>New Reimbursement Form</Card.Title>
            
            <form>
                <label>
                    Name:
                    <input type="text" name="nameInput" ref={nameRef}/>
                </label>
                <label>
                    Reason:
                <input type="text" name="reasonInput" ref={reasonRef}/>
                </label>
                <label>
                    Notes:
                <input type="text" name="notesInput" ref={notesRef}/>
                </label>
                <button onClick={onClick}>Submit</button>
            </form>
        </Card.Body>
        </Card>
        </Container>  
        </>
    );
}