const http = require("http");
const express = require("express");
const socketio = require("socket.io");
const fs = require('fs');
const cors = require('cors');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io=new socketio.Server(server,{
    cors:{
        origin:"*"
    }
});


app.use(cors());  
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

let connectedUsers = [];
const messages = {};
io.on('connection', socket => {
  socket.on('join-server', (username) => {
    const connectedUser = {
      id: socket.id,
      username
    }
    connectedUsers = connectedUsers.filter(user => user.username !== username);
    connectedUsers.push(connectedUser);
    io.emit('new-user', connectedUsers);
  });

  socket.on('send-message', (msg, sender, receiver) => {
    const receiverId = connectedUsers.filter(user => user.username === receiver)[0].id;
    io.to(receiverId).emit('new-message', {msg,sender});
  });

  socket.on('disconnect', () => {
    connectedUsers = connectedUsers.filter(u => u.id !== socket.id);
    io.emit('new-user', connectedUsers);
  })
});

const users = JSON.parse(fs.readFileSync('data/users/users.json'));

app.post('/api/user/login', (req, res) => {
  const { username, password } = req.body;
  const user = users.find(u => u.user_name === username && u.password === password);
  if (user) {
    console.log(username);
    // io.emit('join-server',username);
    res.status(200).json({ message: 'Authentication successful' });
  } else {
    res.status(401).json({ message: 'Authentication failed' });
  }
});

app.get('/api/user/:username', (req, res) => {
  const username = req.params.username;
  const user = users.find(u => u.user_name === username);
  res.status(200).send(user);
});


app.get('/api/posts', (req, res) => {
  const page = parseInt(req.query.page) || 1; 
  const pageSize = parseInt(req.query.pageSize) || 5;
  const startIndex = (page - 1) * pageSize;
  const endIndex = page * pageSize;
  const allPosts = JSON.parse(fs.readFileSync('data/posts/posts.json'));
  const reversedPosts = allPosts.reverse(); 
  const posts = reversedPosts.slice(startIndex, endIndex);
  res.status(200).send(posts);
})

app.post('/api/posts', (req, res) => {
  const { profileImgSrc, name, username, time, caption, fileType, fileSrc } = req.body;
  const inMemPosts = JSON.parse(fs.readFileSync('data/posts/posts.json'));
  const id = inMemPosts.length + 1;
  
  const post = {
    id,
    profileImgSrc,
    name,
    username,
    time,
    caption,
    fileType,
    fileSrc
  };

  inMemPosts.push(post);
  fs.writeFile('data/posts/posts.json', JSON.stringify(inMemPosts), 'utf8', (err) => {
    if (err) {
      console.error("Error in writing to file:", err);
      res.status(500).send("Error in writing to file");
      return;
    }
    console.log("Post added successfully");
    res.status(200).send("Post added successfully");
  });
});

app.get('/api/post/:id', (req, res) => {
  const id = req.params.id;
  const post = JSON.parse(fs.readFileSync('data/posts/posts.json')).find(p => p.id === id);
  if(post){
    res.status(200).send(post);
  }
  else{
    res.status(404).send('not found');
  }
})
const PORT = process.env.PORT || 5000;

server.listen(PORT, () => console.log(`Server is functioning on port ${PORT}`));
