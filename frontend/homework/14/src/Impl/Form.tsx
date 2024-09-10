import React, { useRef, useEffect, useState } from 'react';

export function Form() {
  const nameInputRef = useRef<HTMLInputElement>(null);
  const nameDisplay = useRef<HTMLSpanElement>(null);


  const [myname, setMyname] = useState<string>("")

  useEffect(() => {
    if (nameInputRef.current) {
      nameInputRef.current.focus();
    }
  }, []);

  return (
    <>
      <form>
        <div>
          <label htmlFor="name"  >Name:</label>
          <input type="text" id="name" ref={nameInputRef} value={myname} onChange={(e) => {
            setMyname(e.target.value)
            nameDisplay.current!.innerText = e.target.value;
          }} />
        </div>
        <div>
          <label htmlFor="name">Age:</label>
          <input type="number" id="number" />
        </div>
        <button type="submit">Submit</button>
      </form>

      <p>
        hello <span ref={nameDisplay}>
          Admin
        </span>
      </p>
    </>
  );
}

