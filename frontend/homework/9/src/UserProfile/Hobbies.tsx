
interface IHobby {
  id: number;
  hobby: string;
}

interface HobbiesProps {
  hobbies: IHobby[];
}

export function Hobbies({ hobbies }: Readonly<HobbiesProps>) {
  return (
    <div className="hobbies-container">
      <h2 className="title">Hobbies</h2>
      <ul>
        {hobbies.map(hobby => (
          <li key={hobby.id}>{hobby.hobby}</li>
        ))}
      </ul>
    </div>
  );
}
