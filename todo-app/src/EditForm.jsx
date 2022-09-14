import React, {useState, useEffect} from "react"
import axios from "axios"
import { Input, TextField, Select, MenuItem,FormControl,Button, Paper, Container,Alert } from '@mui/material';

const API_URL = "http://localhost:8080"

export default function EditForm(props) {
  // this useState hook controls the contents in form fields
    const [formData, setFormData] = useState({label:props.item.label, text:props.item.text,isDone: props.item.isDone, id:props.item.id, day:props.item.day})
  function handleChange(event) {//handles change in form inputs
    setFormData(prev => ({...prev, 
      [event.target.name] :event.target.value}))
  }
  function handleSubmit(e) {//makes an api call to edit, displays an error message when it catches an error
    e.preventDefault()
    axios({method: props.method, 
    url:API_URL+ props.url+props.item.id, 
    data: formData}).catch(err => alert("Invalid input. Label must be less than 30 characters and not empty. Text must be less than 150 characters and not empty."))
    .then(window.location.reload())
    
  }
    return <Container maxWidth="xs">
      <Paper elevation={10}>
      <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
        <Input type="text" autoFocus
        name="label" 
        placeholder='Enter Title'
        onChange={handleChange}
        value={formData.label}/>
        <br/>
        <br/>
        <TextField multiline= {true} label='Todo' name="text" onChange={handleChange}
        style={{resize:"none"}}
        minRows="3"
        value={formData.text}/>
        <br/>
        <Select onChange={handleChange}
        name="day"
        value={formData.day}>
          <MenuItem value="Monday">Monday</MenuItem>
          <MenuItem value="Tuesday">Tuesday</MenuItem>
          <MenuItem value="Wednesday">Wednesday</MenuItem>
          <MenuItem value="Thursday">Thursday</MenuItem>
          <MenuItem value="Friday">Friday</MenuItem>
          <MenuItem value="Saturday">Saturday</MenuItem>
          <MenuItem value="Sunday">Sunday</MenuItem>
          <MenuItem value="TODO">TODO</MenuItem>
          <MenuItem value="DOING">DOING</MenuItem>
          <MenuItem value="DONE">DONE</MenuItem>
        </Select>
        <br/>
        <Button variant="contained" style={{"backgroundColor":"black"}} onClick={handleSubmit} className="btn">Submit</Button>
      </FormControl>
      </Paper> 
      </Container>
      
}
//gets the current day and sets it to default value of the list when nothing is passed via props
const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
const d = new Date()
EditForm.defaultProps = {
  item: {
    id:"",
    day: weekday[d.getDay()]
  }
}