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


// In-memory storage for transaction history
let transactionHistory = [];

app.get('/basedata', (req, res) => {
    const baseData = {
        name: 'Zomato',
        initialPrice: 100
    };
    res.json(baseData);
});



io.on('connection', (socket) => {
    console.log('A user connected');
    io.emit('initialData', {
        transactionHistory
    });

    setInterval(() => {
        //random price change between -100 and 100
        const priceChange = Math.floor(Math.random() * 201) - 100;
        io.emit('update', priceChange);
    }, 5000);

    socket.on('disconnect', () => {
        console.log('User disconnected');
    });
});

const PORT= process.env.PORT || 3000;
server.listen(PORT,()=>{
    console.log("Server started");
});
