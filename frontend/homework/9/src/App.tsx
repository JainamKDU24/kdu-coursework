import { UserDisplay } from './UserProfile/UserDisplay';

function App() {
  let userDetails = {
      "name": "Jainam",
      "fullName": "Jainam Gandhi",
      "qualification": "SDE Intern",
      "skills": [
          {
              "id": 1,
              "skill": "Python"
          },
          {
              "id": 2,
              "skill": "Java"
          }
      ],
      "hobbies": [
          {
              "id": 1,
              "hobby": "Cricket"
          }
      ]
  }
  return (
    <UserDisplay user={userDetails} />
  );
}

export default App;
