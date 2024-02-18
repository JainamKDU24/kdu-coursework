document.querySelector(".login-btn").addEventListener("click", () => {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  
  fetch("/api/user/login", {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
      },
    body: JSON.stringify({ username, password }),
  }).then((response) => {
      if (response.ok) {
        console.log("Authentication successful");
        window.location.href = `home.html?username=${username}`;
      } else {
        console.error("Authentication failed");
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});
