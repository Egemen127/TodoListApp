import React, { useState } from "react"
import axios from "axios"
import EditForm from "./EditForm"
import { Card, Dialog, Typography } from "@mui/material"
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';

export default function Item(props) {
    const [editForm, setEditForm] = useState(false)
    //done constant determines the color based on isDone field of the todo object
    const done = props.p.isDone? "success": "error"
    function deleteItem() {//deletes the todo from the db
        axios({url:"http://localhost:8080/delete/"+props.p.id, method:"delete"})
        .then(window.location.reload())
    }

    return (<Card  sx={{m:1, display:"flex", position:"relative", justifyContent:"space-evenly"}}>
        <CardContent>{/*Title and content of the task item*/}
        <Typography variant="h5" component="p">{props.p.label}</Typography>
        <Typography variant="subtitle1" component="div"> {props.p.text}</Typography>
        </CardContent>
        <CardActions>{/*Delete, edit, mark done-not done actions*/}
        {(props.p.day!="DONE"&&props.p.day!="DOING"&&props.p.day!="TODO")&&<Button color={done} variant="contained" onClick={() => props.flip(props.p)}>{props.p.isDone? "Task Done": "Not Done"}</Button>}
        <Button color="error" onClick={deleteItem}>Delete</Button>
        <Button onClick={() => setEditForm(true)}>Edit</Button>
        <Dialog open={editForm} onClose={()=>setEditForm(false)}>{/*Edit form appears in a dialog box when edit button is clicked*/}
            <Typography textAlign="center" variant="h3">Edit Task</Typography>
            <EditForm item={props.p} method="put" 
        url="/edit/"/><br/>
        </Dialog>
        </CardActions>
    </Card>)
}