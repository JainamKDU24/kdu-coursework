import { useEffect, useState } from 'react';
import * as styles from '../../styles/summarizerStyles'
import worker from '../../utils/worker?worker';

interface SummaryData {
  company: string;
  bestBuyDate: string;
  bestSellDate: string;
  bestBuyPrice: number;
  bestSellPrice: number;
  maxProfit: number;
}

export function Summarizer() {
  const [summary, setSummary] = useState<SummaryData[]>([]);

  useEffect(() => {
    
    const fetchDataWorker = async () => {
      const startTime = performance.now(); 
      const workerInstance = new worker();
  
      workerInstance.addEventListener('message', (event) => {
        
        setSummary(event.data);
        const endTime = performance.now(); 
        const elapsedTime = endTime - startTime; 
        console.log('Time taken by web worker (ms):', elapsedTime);
      });
  
      workerInstance.postMessage('FETCH_DATA');
    };
  
    fetchDataWorker();
  }, []);
  

  return (
    <styles.SummaryContainer>
      {summary.map((item, index) => (
        <styles.Items key={index}>
          <styles.FlexContainerStyles>
            <styles.FlexItemLeftStyles>
              <styles.Company>{item.company}</styles.Company>
              <styles.ProfitMargin>Profit margin: {item.maxProfit}</styles.ProfitMargin>
            </styles.FlexItemLeftStyles>
            <styles.FlexItemRightStyles>
              <styles.BuyDetails>Buy: {item.bestBuyPrice} on {item.bestBuyDate}</styles.BuyDetails>
              <styles.SellDetails>Sell: {item.bestSellPrice} on {item.bestSellDate}</styles.SellDetails>
            </styles.FlexItemRightStyles>
          </styles.FlexContainerStyles>
        </styles.Items>
      ))}
    </styles.SummaryContainer>
  );
}
