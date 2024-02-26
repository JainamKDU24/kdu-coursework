import React, { CSSProperties, useRef } from 'react';

export function Scroll()  {
    const scrollToTopRef = useRef<HTMLDivElement>(null);

    const scroll = () => {
      if (scrollToTopRef.current) {
        scrollToTopRef.current.scrollTo({top:0,behavior:'smooth'});
      }
    };const Scroll = () => {
        const scrollToTopRef = useRef<HTMLDivElement>(null);

        const scrollToTop = () => {
          if (scrollToTopRef.current) {
            scrollToTopRef.current.scrollTo({top:0,behavior:'smooth'});
          }
        };
        const styleList:{[key:string]:CSSProperties}  =
        {
          paragraph : {
            width:'600px',
          }
        }  
        return (
          <div ref={scrollToTopRef} style={{ height: '400px', overflow: 'auto' }}>
            <p style={styleList.paragraph}>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Modi dolorum, odit numquam eum, rem excepturi dolorem praesentium vel, odio facere aspernatur perspiciatis minima saepe vero voluptatem officiis? Minima, totam id!
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo quam veritatis repudiandae vitae. Sunt sit a sint adipisci temporibus quidem ipsum voluptatem, hic, laboriosam, tenetur qui soluta odio repellat voluptas.
            </p>
            <button onClick={scrollToTop}>Scroll To Top</button>


          </div>
        );
      };

    const styleList:{[key:string]:CSSProperties}  =
    {
      paragraph : {
        width:'100px',
      }
    }  
    return (
      <div ref={scrollToTopRef} style={{ height: '400px', overflow: 'auto' }}>
        <p style={styleList.paragraph}>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Modi dolorum, odit numquam eum, rem excepturi dolorem praesentium vel, odio facere aspernatur perspiciatis minima saepe vero voluptatem officiis? Minima, totam id!
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo quam veritatis repudiandae vitae. Sunt sit a sint adipisci temporibus quidem ipsum voluptatem, hic, laboriosam, tenetur qui soluta odio repellat voluptas.
        </p>
        <button onClick={scroll}>Scroll To Top</button>


      </div>
    );
  }
