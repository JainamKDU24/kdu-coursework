const apiUrl: string = 'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json';

interface StockData {
  date: string;
  prices: number[];
}

interface CompanyData {
  company: string;
  data: StockData[];
}

interface SummaryData {
  company: string;
  bestBuyDate: string;
  bestSellDate: string;
  bestBuyPrice: number;
  bestSellPrice: number;
  maxProfit: number;
}

export async function fetchData(): Promise<SummaryData[]> {
  try {
    const response = await fetch(apiUrl);
    const data: CompanyData[] = await response.json();

    const summaryData: SummaryData[] = data.map((companyData: CompanyData) => {
      const { company, data: stockData } = companyData;

      let maxProfit: number = 0;
      let bestBuyDate: string = '';
      let bestSellDate: string = '';
      let bestBuyPrice: number = -1;
      let bestSellPrice: number = -1;

      if (stockData && stockData.length > 0) {
        for (const element of stockData) {
          const { date, prices } = element;

          if (prices && prices.length > 0) {
            for (let buyIndex = 0; buyIndex < prices.length; buyIndex++) {
              const buyPrice: number = prices[buyIndex];

              for (let sellIndex = buyIndex + 1; sellIndex < prices.length; sellIndex++) {
                const sellPrice: number = prices[sellIndex];
                const profit: number = sellPrice - buyPrice;

                if (profit > maxProfit && stockData[sellIndex]) {
                  maxProfit = profit;
                  bestBuyDate = date;
                  bestSellDate = stockData[sellIndex].date;
                  bestBuyPrice = buyPrice;
                  bestSellPrice = sellPrice;
                }
              }
            }
          }
        }
      }

      if (maxProfit > 0) {
        return {
          company,
          bestBuyDate,
          bestSellDate,
          bestBuyPrice,
          bestSellPrice,
          maxProfit,
        };
      } else {
        return null; 
      }
    });

    return summaryData.filter((item) => item !== null) as SummaryData[];
  } catch (error) {
    console.error('Failed to fetch data:', error);
    return [];
  }
}
  

// Handle messages sent from the main thread
self.addEventListener('message', async (event) => {
  const action = event.data;

  if (action === 'FETCH_DATA') {
    const data = await fetchData();
    self.postMessage(data);
  }
});
