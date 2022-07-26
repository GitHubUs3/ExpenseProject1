import React from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export const ListItem = ({e, setEmp, removeItem, setUpdate, update}) => {
  
    const handleDelete = async () => {
        try {
           const s = await axios.delete(`http://localhost:8080/expense-reimburse-project/list-form?id=${e.expenseId}`);
            setEmp(e.filter((employee) => {return e.expenseId !== employee.id}));
            //removeItem(e.expenseId);// set update to opposite val of current update val - is bool only T or F
            if(s){// -> refresh page w/ get
                axios.get(`http://localhost:8080/expense-reimburse-project/list-form`)
                .then((resp) => (setEmp(resp.data)))
            }
        }
        catch (err) {
            console.log(err);
        }
    }

    const handleApprove = async () => {
        try {
           const s = await axios.post(`http://localhost:8080/expense-reimburse-project/list-form?id=${e.expenseId}`,{
                name: e.name,
                reason: e.reason,
                notes: e.notes,
                status: 2
            }) .then(setA(true),
           ); // update status -> update state -> store new data in setEmp 
           if(s){// -> refresh page w/ get
            axios.get(`http://localhost:8080/expense-reimburse-project/list-form`)
            .then((resp) => (setEmp(resp.data)))
           }

        } catch (error) {
            console.log(error);
        }
    }

    const handleDeny = async () => {
        try {
           const s = await axios.post(`http://localhost:8080/expense-reimburse-project/list-form?id=${e.expenseId}`,{
                name: e.name,
                reason: e.reason,
                notes: e.notes,
                status: 3
            }) .then(setA(true));
            if(s){
                axios.get(`http://localhost:8080/expense-reimburse-project/list-form`)
                .then((resp) => (setEmp(resp.data)))
               }

        } catch (error) {
            console.log(error);
        }
    }

    // variable for keeping state of buttons from reverting (to original status) once selected
    const [a, setA] = useState(false);

    useEffect(() => {// checks if status was already changed
        if(e.status === 2 || e.status === 3){
            console.log(e.status)
            setA(true);
        }
        else{
            setA(false);
        }
    })

    return (
    <>
    <tr>
            <td>{e.name}</td>
            <td>{e.reason}</td>
            <td>{e.notes}</td>
            <td>{e.status}</td>
            <td>
            <button onClick={handleApprove} disabled={a}>Approve</button>
            <button onClick={handleDeny} disabled={a}>Deny</button>
            <button onClick={handleDelete}>Delete</button>
            </td>
    </tr>
    </>
  )
}
