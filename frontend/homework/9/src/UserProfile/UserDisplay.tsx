import { Hobbies } from "./Hobbies";
import { Skills } from "./Skills";
import "./UserDisplay.css";

interface ISkill {
  id: number;
  skill: string;
}
interface IHobby {
  id: number;
  hobby: string;
}
interface IUser{
  name: string,
  fullName: string,
  qualification: string,
  skills:ISkill[],
  hobbies: IHobby[]
}

interface UserDisplayProps {
  user: IUser;
}
export function UserDisplay({user}:Readonly<UserDisplayProps>) {
  return (
    <div className="UserDisplay">
      <header className="name">{user.name}</header>
      <header className="fullName">{user.fullName}</header>
      <header className="qualification">{user.qualification}</header>
      <div className="skills-hobbies">
        <Skills skills={user.skills}/>
        <Hobbies hobbies={user.hobbies}/>
      </div>
    </div>
  )
}
