const express=require('express');
const router=express.Router();
const posts=require('../posts');
const uuid=require('uuid')

//Get Posts
router.get('/',(req,res)=>res.json(posts));

//Get Post with id
router.get('/:id',(req,res)=>{
    const found=posts.some(post=>post.id==(req.params.id));

    if(found){
        res.json(posts.filter(post=>post.id==(req.params.id)));
    }
    else{
        res.status(400).json({
            msg:`No post with id : ${req.params.id}`
        });
    }
});

//Create Post
router.post('/',(req,res)=>{
    const {name,username,content}=req.body;
    console.log(req.body);
    if(content){
        let id=uuid.v4();
        const newpost={
            "id":id,
            "name":name,
            "username":username,
            "content":content
        }
        posts.push(newpost);
        console.log(newpost);
        res.status(201).send(newpost)
    }
    else{
        res.status(400).json({
            msg:`No content to add`
        });
    }
});

module.exports=router;