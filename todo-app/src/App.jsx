import { useEffect, useState } from 'react'
import './App.css'
import Item from "./Item"
import axios from "axios"
import EditForm from './EditForm'
import { Typography,Button,Grid,ButtonGroup,Card, Divider} from '@mui/material'
import { useNavigate } from "react-router-dom"
import { alpha, styled } from '@mui/material/styles';

const API_URL = "http://localhost:8080"
function App(props) {
  //TODO
  // navigates back to the login page if the user is not authenticated
  if(!localStorage.getItem("current_jwt")) {
    nav("/")
  }
  axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem("current_jwt");
  const nav = useNavigate()
  //gets the current day and displays that days tasks when the page loads
  const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
  const d = new Date()
  const [todo, setTodo] = useState([])
  const [day,setDay] = useState(weekday[d.getDay()])
  useEffect(() => {
    axios(API_URL).then(res => setTodo(res.data))
  }, [])

  function flip(e) {//function to flip task done button
    axios({
      method: "put",
      url: API_URL+"/edit/"+e.id,
      data: {...e, isDone: !e.isDone}
    }).then(setTodo(prev => prev.map(item=>{if(e.id==item.id)
    return {...item, isDone: !item.isDone}
    else
  return item})))
  }
  
  return (
    <div className="App">
      <div style={{"display":"flex","justifyContent":"end"}}><Typography variant="h4">Current user: <b>{localStorage.getItem("current_user")}</b></Typography></div>
        <ButtonGroup variant="contained" aria-label="outlined primary button group" size="large">{/*these buttons are for displaying each days task*/}
          <Button className="btn" style={{backgroundColor:day=="Monday"?"#B6FF69":"black"}} 
          onClick={()=>setDay("Monday")}>Monday</Button>
          <Button className="btn" style={{backgroundColor:day=="Tuesday"?"#B6FF69":"black"}} onClick={()=>setDay("Tuesday")}>Tuesday</Button>
          <Button className="btn" style={{backgroundColor:day=="Wednesday"?"#B6FF69":"black"}} onClick={()=>setDay("Wednesday")}>Wednesday</Button>
          <Button className="btn" style={{backgroundColor:day=="Thursday"?"#B6FF69":"black"}} onClick={()=>setDay("Thursday")}>Thursday</Button>
          <Button className="btn" style={{backgroundColor:day=="Friday"?"#B6FF69":"black"}} 
          onClick={()=>setDay("Friday")}>Friday</Button>
          <Button className="btn" style={{backgroundColor:day=="Saturday"?"#B6FF69":"black"}} onClick={()=>setDay("Saturday")}>Saturday</Button>
          <Button className="btn" style={{backgroundColor:day=="Sunday"?"#B6FF69":"black"}} 
        onClick={()=>setDay("Sunday")}>Sunday</Button>
        </ButtonGroup>

      <Typography variant="h3">{day}</Typography>
      {/* turns the data into components */}
      {todo.filter(e => e.day ==day).length > 0? todo.filter(e => e.day ==day).map(e => <Item p={e} key={e.id} flip={flip} setFormData/>): <Typography variant="h4">No tasks for {day}</Typography>}
      <Typography variant="h5">Add Todo</Typography>
      <EditForm method = "post" url="/add"/>
      <Button variant="contained" color="error" sx={{margin:1}} onClick={()=>{
        if(confirm("Do you want to logout?")){
          localStorage.clear()
        nav("/")
        }
      }}>Logout</Button>
      {/* Kanban board */}
      <Typography variant="h2">PROJECT PROGRESS</Typography>
      <Grid container spacing={1}>
        <Grid item xs={4}>
          <Card variant="outlined">
            <Typography variant="h4">TODO</Typography>
            <Divider/>
            {todo.filter(e => e.day =="TODO").length > 0? todo.filter(e => e.day =="TODO").map(e => <Item p={e} key={e.id} flip={flip} setFormData/>): <h2>NOTHING IN TODO</h2>}
          </Card>
        </Grid>
        <Grid item xs={4}>
          <Card variant="outlined">
            <Typography variant="h4">DOING</Typography>
            <Divider/>
            {todo.filter(e => e.day =="DOING").length > 0? todo.filter(e => e.day =="DOING").map(e => <Item p={e} key={e.id} flip={flip} setFormData/>): <h2>NOTHING IN DOING</h2>}
          </Card>
        </Grid>
        <Grid item xs={4}>
          <Card variant="outlined">
            <Typography variant="h4">DONE</Typography>
            <Divider/>
            {todo.filter(e => e.day =="DONE").length > 0? todo.filter(e => e.day =="DONE").map(e => <Item p={e} key={e.id} flip={flip} setFormData/>): <h2>NOTHING IN DONE</h2>}
          </Card>
        </Grid>
      </Grid>
    </div>
  )
}

export default App
