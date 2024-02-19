
interface ISkill {
  id: number;
  skill: string;
}

interface SkillsProps {
  skills: ISkill[];
}

export function Skills({ skills }: Readonly<SkillsProps>) {
  return (
    <div className="skills-container">
      <h2 className="title">Skills</h2>
      <ul>
        {skills.map(skill => (
          <li key={skill.id}>{skill.skill}</li>
        ))}
      </ul>
    </div>
  );
}
