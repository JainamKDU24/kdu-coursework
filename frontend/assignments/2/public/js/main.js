// Post
const postButton = document.querySelectorAll(".tweet-btn");
const posts = document.getElementById("posts");
postButton.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    const profileImgSrc = document.getElementById('profile-img').src;
    const name = document.querySelector('.profile-name').textContent;
    const username = document.querySelector('.profile-username').textContent;
    const time = fetchTime();
    let caption;
    let mediaType;
    let mediaSource;

    const par = e.target.parentElement;
    let postInput;
    let mediaInput;
    if (par.classList.contains("post-btn-container")) {
      postInput =
        e.target.parentElement.parentElement.parentElement.querySelector(
          ".post-input"
        );
      mediaInput = document.getElementById('file-input');
    } else {
      postInput =
        e.target.parentElement.parentElement.parentElement.parentElement.parentElement.querySelector(
          ".post-input"
        );
      mediaInput = document.getElementById('file-input-m');
      header.style.display = 'flex';
      topContainer.style.display = 'flex';
      posts.style.display = 'initial';
      plusBtn.style.display = 'initial';
      mobileTweetBox.style.display = 'none';
    }
    const file = mediaInput.files[0];
    const reader = new FileReader();
    if(file){
      console.log("here");
      reader.onload = () => {
        mediaType = file.type.split('/')[0];
        console.log("mediaType", mediaType);
        mediaSource = reader.result;
        console.log(mediaSource);
        caption = postInput.value;
        caption = postInput.value;
        if(caption === ''){
            return;
        }
        else{
          mediaInput.value = '';
          document.querySelector('.file-upload-container').style.display = 'none';
          postInput.value = "";
        }
        tweetBtn[0].style.opacity = 0.5;
        tweetBtn[1].style.opacity = 0.5;
        console.log(mediaType, mediaSource);
        addPost(profileImgSrc, name, username, time, caption, mediaType, mediaSource);
      }
      reader.readAsDataURL(file);
    }
    else{
      caption = postInput.value;
        if(caption === ''){
            return;
        }
        else{
          postInput.value = "";
        }
        tweetBtn[0].style.opacity = 0.5;
        tweetBtn[1].style.opacity = 0.5;
        addPost(profileImgSrc, name, username,time, caption, null, null);
    }
  });

});
// Post

// Mobile Nav Toggling
const toggleButton = document.getElementById("toggler");
const container = document.getElementById("container-2");
const div = document.querySelector(".navigation-section");
let toggle = false;

toggleButton.addEventListener("click", () => {
  container.style.opacity = toggle ? 1 : 0;
  div.classList.toggle("show");
  toggle = !toggle;
});
// Mobile Nav Toggling

// Input Modification
const postInput = document.getElementsByClassName("post-input");
const tweetBtn = document.getElementsByClassName("tweet-btn");
postInput[0].addEventListener("keyup", () => {
  if (postInput[0].value !== "") {
    tweetBtn[0].style.opacity = 1;
  } else {
    tweetBtn[0].style.opacity = 0.5;
  }
});

postInput[1].addEventListener("keyup", () => {
  if (postInput[1].value !== "") {
    tweetBtn[1].style.opacity = 1;
  } else {
    tweetBtn[1].style.opacity = 0.5;
  }
});
// Input Modification

//Like Post
const likeBtns = document.querySelectorAll(".like-btn");
const likeSvg = `<svg class="like-btn" style="width:18.75px; fill:#f91880" viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>`;
const dislikeSvg = `<svg class="like-btn logo" style="width=18.75px; fill:#71767a;" viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>`;

likeBtns.forEach((btn) => {
  let like = true;
  btn.addEventListener("click", (e) => {
    const container = e.target.parentElement;

    if (like) {
      container.innerHTML = likeSvg;
    } else {
      container.innerHTML = dislikeSvg;
    }
    like = !like;
  });
});
//Like Post

//Mobile TweetBox
const plusBtn = document.getElementById('float-btn');
const backBtn = document.getElementById('back-btn');
const mobileTweetBox = document.querySelector('.mobile-tweet-box');
const header = document.querySelector('.header');
const topContainer = document.querySelector('.top-container');

plusBtn.addEventListener('click', () => {
  header.style.display = 'none';
  topContainer.style.display = 'none';
  posts.style.display = 'none';
  plusBtn.style.display = 'none';
  mobileTweetBox.style.display = 'initial';
})

backBtn.addEventListener('click', () => {
  header.style.display = 'flex';
  topContainer.style.display = 'flex';
  posts.style.display = 'initial';
  plusBtn.style.display = 'flex';
  mobileTweetBox.style.display = 'none';
})
//Mobile TweetBox


// Media Upload
document.querySelector('.file-upload').addEventListener('click', () => {
  document.getElementById('file-input').click();
})

document.getElementById('file-input').addEventListener('change', (event) => {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    const mediaType = file.type.split('/')[0];
    if (mediaType === 'image') {
      const imagePreview = document.getElementById('image-preview');
      imagePreview.src = reader.result;
      imagePreview.style.display = 'block';
      document.querySelector('.file-upload-container').style.display = 'initial';
      document.getElementById('video-preview').style.display = 'none';
    } else if (mediaType === 'video') {
      const videoPreview = document.getElementById('video-preview');
      videoPreview.src = reader.result;
      videoPreview.style.display = 'block';
      document.querySelector('.file-upload-container').style.display = 'initial';
      document.getElementById('image-preview').style.display = 'none';
    }
  };

  reader.readAsDataURL(file);
});

document.querySelector('.file-upload-m').addEventListener('click', () => {
  document.getElementById('file-input-m').click();
})

document.getElementById('file-input-m').addEventListener('change', (event) => {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    const mediaType = file.type.split('/')[0];
    if (mediaType === 'image') {
      const imagePreview = document.getElementById('image-preview-m');
      imagePreview.src = reader.result;
      imagePreview.style.display = 'block';
      document.querySelector('.file-upload-container-m').style.display = 'initial';
      document.getElementById('video-preview-m').style.display = 'none';
    } else if (mediaType === 'video') {
      const videoPreview = document.getElementById('video-preview-m');
      videoPreview.src = reader.result;
      videoPreview.style.display = 'block';
      document.querySelector('.file-upload-container-m').style.display = 'initial';
      document.getElementById('image-preview-m').style.display = 'none';
    }
  };

  reader.readAsDataURL(file);
});
// Media Upload


// Post Creation
function createPostHTML(profileImgSrc, name, username, time, caption, mediaType, mediaSource) {
  // Create div elements
  const profileImgDiv = document.createElement('div');
  profileImgDiv.classList.add('profile-img');
  
  const img = document.createElement('img');
  img.src = profileImgSrc;
  img.alt = "profile";
  profileImgDiv.appendChild(img);

  const content = document.createElement('div');
  content.classList.add('content');

  const postInfoDiv = document.createElement('div');
  postInfoDiv.classList.add('post-info');

  const postProfileInfoDiv = document.createElement('div');
  postProfileInfoDiv.classList.add('post-profile-info');

  const postUserSpan = document.createElement('span');
  postUserSpan.classList.add('post-user');
  postUserSpan.innerHTML = `${name} <span class="post-username">${username} · ${time}</span>`;
  postProfileInfoDiv.appendChild(postUserSpan);

  const dotsDisplay = document.createElement('div');
  dotsDisplay.classList.add('post-three-dot-option');

  const icon1 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon1.setAttribute('class', 'logo');
  icon1.setAttribute('style', 'fill:#71767a');
  icon1.setAttribute('viewBox', '0 0 24 24');
  icon1.setAttribute('aria-hidden', 'true');
  icon1.innerHTML = '<g><path d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z"></path></g>';
  dotsDisplay.appendChild(icon1);

  postInfoDiv.appendChild(postProfileInfoDiv);
  postInfoDiv.appendChild(dotsDisplay);

  const postCaptionDiv = document.createElement('div');
  postCaptionDiv.classList.add('post-caption');
  postCaptionDiv.innerHTML = caption;

  const fileUploadedContainer = document.createElement('div');
  fileUploadedContainer.classList.add('file-uploaded');
  let fileContent = null;
  if(mediaType && mediaSource){
    if (mediaType === 'image') {
      fileContent = document.createElement('img');
      fileContent.src = mediaSource;
      fileContent.alt = 'Uploaded Image';
    } else if (mediaType === 'video') {
      fileContent = document.createElement('video');
      fileContent.src = mediaSource;
      fileContent.controls = true;
      fileContent.autoplay = true;
      fileContent.muted = true;
    }
    fileUploadedContainer.appendChild(fileContent);
  }
  const iconContainer = document.createElement('div');
  iconContainer.classList.add('post-icons');

  // Creating Icons
  // Icon 1
  const iconDiv1 = document.createElement('div');
  iconDiv1.classList.add('icon');

  const logo1 = document.createElement('div');
  logo1.classList.add('logo-container');

  const svg1 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  svg1.setAttribute('class', 'logo');
  svg1.setAttribute('style', 'fill:#71767a');
  svg1.setAttribute('viewBox', '0 0 24 24');
  svg1.setAttribute('aria-hidden', 'true');
  svg1.innerHTML = '<path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path>';

  const counterDiv1 = document.createElement('div');
  counterDiv1.classList.add('counter');

  logo1.appendChild(svg1);
  iconDiv1.appendChild(logo1);
  iconDiv1.appendChild(counterDiv1);

  // Icon 2
  const iconDiv2 = document.createElement('div');
  iconDiv2.classList.add('icon');

  const logo2 = document.createElement('div');
  logo2.classList.add('logo-container');

  const icon2 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon2.setAttribute('class', 'logo');
  icon2.setAttribute('style', 'fill:#71767a');
  icon2.setAttribute('viewBox', '0 0 24 24');
  icon2.setAttribute('aria-hidden', 'true');
  icon2.innerHTML = '<path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path>';

  const counterDiv2 = document.createElement('div');
  counterDiv2.classList.add('counter');

  logo2.appendChild(icon2);
  iconDiv2.appendChild(logo2);
  iconDiv2.appendChild(counterDiv2);

  // Icon 3
  const iconDiv3 = document.createElement('div');
  iconDiv3.classList.add('icon');

  const logo3 = document.createElement('div');
  logo3.classList.add('logo-container');

  const icon3 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon3.setAttribute('class', 'logo');
  icon3.setAttribute('style', 'fill:#71767a');
  icon3.setAttribute('viewBox', '0 0 24 24');
  icon3.setAttribute('aria-hidden', 'true');
  icon3.innerHTML = '<path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>';

  const counterDiv3 = document.createElement('div');
  counterDiv3.classList.add('counter');

  logo3.appendChild(icon3);
  iconDiv3.appendChild(logo3);
  iconDiv3.appendChild(counterDiv3);

  // Icon 4
  const iconDiv4 = document.createElement('div');
  iconDiv4.classList.add('icon');

  const logo4 = document.createElement('div');
  logo4.classList.add('logo-container');

  const icon4 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon4.setAttribute('class', 'logo');
  icon4.setAttribute('style', 'fill:#71767a');
  icon4.setAttribute('viewBox', '0 0 24 24');
  icon4.setAttribute('aria-hidden', 'true');
  icon4.innerHTML = '<path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path>';

  const counterDiv4 = document.createElement('div');
  counterDiv4.classList.add('counter');

  logo4.appendChild(icon4);
  iconDiv4.appendChild(logo4);
  iconDiv4.appendChild(counterDiv4);

  // Icon 5
  const iconDiv5 = document.createElement('div');
  iconDiv5.classList.add('icon');
  iconDiv5.classList.add('last-icon');

  const logo5 = document.createElement('div');
  logo5.classList.add('logo-container');

  const icon5 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon5.setAttribute('class', 'logo');
  icon5.setAttribute('style', 'fill:#71767a');
  icon5.setAttribute('viewBox', '0 0 24 24');
  icon5.setAttribute('aria-hidden', 'true');
  icon5.innerHTML = '<path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path>';

  const counterDiv5 = document.createElement('div');
  counterDiv5.classList.add('counter');

  logo5.appendChild(icon5);
  iconDiv5.appendChild(logo5);
  iconDiv5.appendChild(counterDiv5);

  // Icon 6
  const iconDiv6 = document.createElement('div');
  iconDiv6.classList.add('icon');
  iconDiv6.classList.add('share-icon');

  const logo6 = document.createElement('div');
  logo6.classList.add('logo-container');

  const icon6 = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  icon6.setAttribute('class', 'logo');
  icon6.setAttribute('style', 'fill:#71767a');
  icon6.setAttribute('viewBox', '0 0 24 24');
  icon6.setAttribute('aria-hidden', 'true');
  icon6.innerHTML = '<path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path>';

  const counterDiv6 = document.createElement('div');
  counterDiv6.classList.add('counter');

  logo6.appendChild(icon6);
  iconDiv6.appendChild(logo6);
  iconDiv6.appendChild(counterDiv6);

  // Adding the icons to the div
  iconContainer.appendChild(iconDiv1);
  iconContainer.appendChild(iconDiv2);
  iconContainer.appendChild(iconDiv3);
  iconContainer.appendChild(iconDiv4);
  iconContainer.appendChild(iconDiv5);
  iconContainer.appendChild(iconDiv6);

  const postDiv = document.createElement('div');
  postDiv.classList.add('post');
  postDiv.appendChild(profileImgDiv);
  content.appendChild(postInfoDiv);
  content.appendChild(postCaptionDiv);
  content.appendChild(fileUploadedContainer);
  content.appendChild(iconContainer);
  postDiv.appendChild(content);

  return postDiv;
}
// Post

function createPost(post) {
  const {profileImgSrc, name, username, time, caption, mediaType, mediaSource} = post;
  const postDiv = createPostHTML(profileImgSrc,name,username,time,caption,mediaType,mediaSource);
  posts.append(postDiv);
}

function fetchTime(){
    const currentDate = new Date();
    const hours = currentDate.getHours();
    let minutes = currentDate.getMinutes();
    if(minutes < 10){
        minutes = '0'+minutes;
    }
    return `${hours}:${minutes}`;
}
const addPost = async (profileImgSrc, name, username, time, caption, mediaType, mediaSource) => {
  try {
    const response = await fetch("/api/posts", {
      method: "POST",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({profileImgSrc, name, username, time, caption, mediaType, mediaSource }),
    });
    if (response.ok) {
      console.log("Post added successfully");
      setTimeout(() => {
        location.reload();
      },1000)
    } else {
      console.error("Add Post failed");
    }
  } catch (error) {
    console.error("Error:", error);
  }
};

const pageSize = 5; 
let currentPage = 1;
const pageRender = async (username,page = 1) => {
  try {
    const userResponse = await fetch(`/api/user/${username}`);
    const userData = await userResponse.json();
    
    document.getElementById('profile-img').src = userData.profile_url;
    document.getElementById('profile-img-m').src = userData.profile_url;
    document.getElementById('tweet-box-img').src = userData.profile_url;
    document.getElementById('tweet-box-img-m').src = userData.profile_url;
    document.getElementById('header-img').src = userData.profile_url;
    document.querySelector('.profile-name').textContent = userData.name;
    document.querySelector('.profile-username').textContent = '@' + userData.user_name;
    document.querySelector('.name').textContent = userData.name;
    document.querySelector('.username').textContent = '@' + userData.user_name;

    const response = await fetch(`/api/posts?page=${page}&pageSize=${pageSize}`);
    if (response.ok) {
      const data = await response.json();
      posts.innerHTML = ''; 
      
      data.forEach(post => createPost(post));
    } else {
      console.error('Error fetching posts:', response.statusText);
    }
  } catch (error) {
    console.error('Error fetching posts:', error);
  }
};

const fetchPosts = async (page) => {
  try {
    const response = await fetch(`/api/posts?page=${page}&pageSize=${pageSize}`);
    if (response.ok) {
      const data = await response.json();
      data.forEach(post => createPost(post));
    } else {
      console.error('Error fetching posts:', response.statusText);
    }
  } catch (error) {
    console.error('Error fetching posts:', error);
  }
};

let isLoadingNextPage = false;
const isAtBottom = () => {
  const container2 = document.querySelector('.container-2');
  return container2.scrollTop + container2.clientHeight >= container2.scrollHeight;
};

document.querySelector('.container-2').addEventListener('scroll', async () => {
  if (isAtBottom() && !isLoadingNextPage) {
    isLoadingNextPage = true;
    setTimeout(async () => {
      currentPage++; 
      await fetchPosts(currentPage); 
      isLoadingNextPage = false;
    }, 1000); 
  }
});

const params = new URLSearchParams(window.location.search);
const username = params.get('username');

pageRender(username,currentPage);

const homeButton = document.getElementById('home-btn');
const messageButton = document.getElementById('msg-btn');
const homeDiv = document.querySelector('.container-2');
const msgDiv = document.querySelector('.container-3');
const homeButtonM = document.getElementById('home-btn-m');
const messageButtonM = document.getElementById('msg-btn-m');
const chatDiv = document.querySelector('.chat-container');
homeButton.addEventListener('click', () => {
  homeDiv.style.display = 'initial';
  msgDiv.style.display = 'none';
  chatDiv.style.display = 'none';
});
messageButton.addEventListener('click', () => {
  homeDiv.style.display = 'none';
  msgDiv.style.display = 'initial';
  chatDiv.style.display = 'initial';
});
homeButtonM.addEventListener('click', () => {
  homeDiv.style.display = 'initial';
  msgDiv.style.display = 'none';
});
messageButtonM.addEventListener('click', () => {
  homeDiv.style.display = 'none';
  msgDiv.style.display = 'initial';
});


const socket = io();

const connectedUsersList = document.querySelector('.msg-users');

socket.emit('join-server', username);

socket.on('new-user', (connectedUsers) => {
  connectedUsersList.innerHTML = '';
  connectedUsers.forEach(user => {
      msgUser(user);
  });
});

async function msgUser(user){
  const userResponse = await fetch(`/api/user/${user.username}`);
  const userData = await userResponse.json();
  const msgUserDiv = document.createElement('div');
  msgUserDiv.classList.add('msg-user');

  const profileImgDiv = document.createElement('div');
  profileImgDiv.classList.add('msg-user-profile');
  const profileImg = document.createElement('img');
  profileImg.src = userData.profile_url;
  profileImg.alt = 'profile';
  profileImgDiv.appendChild(profileImg);
  const infoDiv = document.createElement('div');
  infoDiv.classList.add('msg-user-info');
  const userNameSpan = document.createElement('span');
  userNameSpan.classList.add('msg-user-name');
  userNameSpan.textContent = userData.name + ' ';
  const userUsernameSpan = document.createElement('span');
  userUsernameSpan.classList.add('msg-user-username');
  userUsernameSpan.textContent = '@' +userData.user_name;
  infoDiv.appendChild(userNameSpan);
  infoDiv.appendChild(userUsernameSpan);

  msgUserDiv.appendChild(profileImgDiv);
  msgUserDiv.appendChild(infoDiv);
  connectedUsersList.append(msgUserDiv);

  msgUserDiv.addEventListener('click', (e) => {
    if (window.innerWidth < 500) {
      document.querySelector('.container-3').style.display = 'none';
      document.querySelector('.chat-container').style.display = 'initial';
      const backBtn = document.querySelector('.chat-back-btn');
      document.querySelector('.bottom-navbar').style.display = 'none';
      backBtn.style.display = 'initial';
      backBtn.addEventListener('click', () => {
        document.querySelector('.container-3').style.display = 'initial';
        document.querySelector('.chat-container').style.display = 'none';
        backBtn.style.display = 'none';
        document.querySelector('.bottom-navbar').style.display = 'flex';
      })
    } 
    const receiverName = msgUserDiv.querySelector('.msg-user-name').textContent;
    let receiverUsername = msgUserDiv.querySelector('.msg-user-username').textContent;
    receiverUsername = receiverUsername.replace('@','');
    document.querySelector('.chat-user-name').textContent = receiverName;
    const messagesDiv = document.querySelector('.messages');
    messagesDiv.innerHTML = '';
    const sendBtn = document.querySelector('.chat-send-btn');
    sendBtn.addEventListener('click', () => {
      const msg = document.getElementById('chat-msg').value;
      if(msg !== ''){
        createMsg(msg, false);
        if(username !== receiverUsername){
          const name = document.querySelector('.profile-name').textContent;
          socket.emit('send-message', msg, name, receiverUsername);
        }
      }
      document.getElementById('chat-msg').value = '';
    })
    
  })
  if(document.querySelector('.chat-user-name').textContent.trim() === ''){
    document.querySelector('.msg-user').click();
  }
};

socket.on('new-message', (messageObject) => {
  if(messageObject.sender.trim() === document.querySelector('.chat-user-name').textContent.trim()){
    createMsg(messageObject.msg,true);
  }
});

function createMsg(msg, receive){
  const messagesDiv = document.querySelector('.messages');
  const messageDiv = document.createElement('div');
  if(receive){
    messageDiv.classList.add('message-receive');
  }
  else{
    messageDiv.classList.add('message-send');
  }
  messageDiv.textContent = msg;
  messagesDiv.append(messageDiv);
}
