const express=require('express');
const http=require('http');
const cors=require('cors');
const socketIo=require('socket.io');

const app=express();
const server=http.createServer(app);
const io=new socketIo.Server(server,{
    cors:{
        origin:"http://127.0.0.1:5500"
    }
});

app.use(cors())
app.use(express.static(__dirname));
app.use(express.json());

app.get("/",(req,res)=>{
    res.json({
        msg:"Hello"
    });
});

io.on("connection",(socket)=>{
    console.log("Connection started");
    socket.on("message",(message)=>{
        console.log("Message: ",message);
        io.except(socket.id).emit("new-message",message);
    })
});

const PORT= process.env.PORT || 3000;
server.listen(PORT,()=>{
    console.log("Server started");
});
