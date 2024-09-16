const socket=io("http://localhost:3000")
        const messageInput=document.getElementById("msgInput")
        const sendButton=document.getElementById("sendMessage")
        const messageOutput=document.getElementById("messages")

        function addMessage(from, message) {
            const element = document.createElement('div');
            element.className = 'message-container'; 

            const circle = document.createElement('div');
            circle.className = 'circle';
            if(from==="You"){
                circle.innerText = 'Y';
            }
            else if(from=="User"){
                circle.innerText = 'U';
                circle.style.backgroundColor='#28a745';
            }

            const messageElement = document.createElement('p');
            messageElement.innerText =  message;

            element.appendChild(circle);
            element.appendChild(messageElement);

            messageOutput.appendChild(element);
        }

        messageInput.addEventListener('input', function() {
            if (messageInput.value.trim() !== '') {
                sendButton.style.opacity = '1'; 
            } else {
                sendButton.style.opacity = '0.5'; 
            }
        });
        sendButton.addEventListener("click",()=>{
            const message=messageInput.value;
            //send particular message over socket
            if(message!==""){
                socket.emit("message",message);
                addMessage("You",message);
                messageInput.value='';
                sendButton.style.opacity = '0.5'; 
            }
        });
        socket.on("new-message",(message)=>{
            addMessage("User",message);
        })