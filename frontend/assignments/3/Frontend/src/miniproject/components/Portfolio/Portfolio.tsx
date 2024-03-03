import React, { useState } from 'react';
import * as styles from '../../styles/portfolioStyles';
import { useSelector } from "react-redux";
import { RootState } from "../../middleware/stores/Store";
import { ITransaction } from "../../middleware/slices/transactionSlice";
import { ClipLoader } from 'react-spinners';

interface IGroupedTransactions {
  [date: string]: ITransaction[];
}
function restructureDate(dateString: string) {
  const dateParts = dateString.split("-");
  const year = dateParts[0];
  const month = dateParts[1];
  const day = dateParts[2];
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  return `${day} ${months[parseInt(month, 10) - 1]} ${year}`;
}

export function restructureTime(timeString: string) {
  const timeComponents = timeString.split("T")[1].split(":");
  const hours = parseInt(timeComponents[0], 10);
  const minutes = timeComponents[1];
  const timePeriods = hours >= 12 ? "PM" : "AM";
  const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
  return `${formattedHours}:${minutes} ${timePeriods}`;
}

export function Portfolio() {
  const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [passedChecked, setPassedChecked] = useState(false);
  const [failedChecked, setFailedChecked] = useState(false);
  const [selectedStartDate, setSelectedStartDate] = useState<string | null>(null);
  const [selectedEndDate, setSelectedEndDate] = useState<string | null>(null);

  const transactionsStatus = useSelector(
    (state: RootState) => state.transactions.status
  );
  const transactionsList = useSelector(
    (state: RootState) => state.transactions.transactionsList
  );

  const uniqueStockNames: string[] = Array.from(
    new Set(
      transactionsList.map(
        (transaction: ITransaction) => transaction.stock_name
      )
    )
  );

function groupTransactionsByDate(transactions: ITransaction[]): IGroupedTransactions {
  return transactions.reduce((groupedTransactions: IGroupedTransactions, transaction: ITransaction) => {
    const date = transaction.timestamp.split('T')[0];
    const transactionsForDate = groupedTransactions[date] || [];
    transactionsForDate.push(transaction);
    groupedTransactions[date] = transactionsForDate;
    return groupedTransactions;
  }, {});
}

function handleCheckboxChange(stock: string) {
  if (selectedStocks.includes(stock)) {
    setSelectedStocks(selectedStocks.filter((selectedStock) => selectedStock !== stock));
  } else {
    setSelectedStocks([...selectedStocks, stock]);
  }
}

function handleSearchChange(event: React.ChangeEvent<HTMLInputElement>) {
  setSearchTerm(event.target.value);
}

function handleStartDateChange(event: React.ChangeEvent<HTMLInputElement>) {
  setSelectedStartDate(event.target.value);
}

function handleEndDateChange(event: React.ChangeEvent<HTMLInputElement>) {
  setSelectedEndDate(event.target.value);
}

  const filteredTransactionsList = transactionsList.filter((transaction) => {
    const passesSearchQuery = searchTerm.trim() === '' ||
      transaction.stock_name.toLowerCase().startsWith(searchTerm.toLowerCase()) ||
      transaction.stock_symbol.toLowerCase().includes(searchTerm.toLowerCase());

    const passesStatusFilter = (!passedChecked || transaction.status === 'Passed') &&
      (!failedChecked || transaction.status === 'Failed');

    const startDate = selectedStartDate ? new Date(selectedStartDate) : null;
    const endDate = selectedEndDate ? new Date(selectedEndDate) : null;
    const transactionDate = new Date(transaction.timestamp);
    let withinSelectedDateRange = true;

    if (startDate && endDate) {
      withinSelectedDateRange = transactionDate >= startDate && transactionDate <= new Date(endDate.setDate(endDate.getDate() + 1));
    } else if (startDate) {
      withinSelectedDateRange = transactionDate >= startDate && transactionDate <= new Date();
    } else if (endDate) {
      withinSelectedDateRange = transactionDate <= new Date(endDate.setDate(endDate.getDate() + 1));
    }

    return passesSearchQuery && passesStatusFilter && withinSelectedDateRange;
  });

  const handleClearFilters = () => {
    setPassedChecked(false);
    setFailedChecked(false);
    setSelectedStocks([]);
    setSearchTerm('');
    setSelectedStartDate('');
    setSelectedEndDate('');

    const startDateInput = document.getElementById('start-date') as HTMLInputElement;
    const endDateInput = document.getElementById('end-date') as HTMLInputElement;
    if (startDateInput && endDateInput) {
      startDateInput.value = '';
      endDateInput.value = '';
    }
    const searchInput = document.getElementById('search') as HTMLInputElement;
    if (searchInput) {
      searchInput.value = '';
    }
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
      checkbox.checked = false;
    });
  };


  return (
    <styles.PortfolioContainer>
      <styles.FilterContainer>
        <styles.FilterHeader>
          <styles.Header>
            <div>Filters</div>
            <styles.ClearAll onClick={handleClearFilters}>Clear All</styles.ClearAll> 
          </styles.Header>
        </styles.FilterHeader>
        <styles.FilterHeader>
          <styles.SearchBar>
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path d="m15.97 17.031c-1.479 1.238-3.384 1.985-5.461 1.985-4.697 0-8.509-3.812-8.509-8.508s3.812-8.508 8.509-8.508c4.695 0 8.508 3.812 8.508 8.508 0 2.078-.747 3.984-1.985 5.461l4.749 4.75c.146.146.219.338.219.531 0 .587-.537.75-.75.75-.192 0-.384-.073-.531-.22zm-5.461-13.53c-3.868 0-7.007 3.14-7.007 7.007s3.139 7.007 7.007 7.007c3.866 0 7.007-3.14 7.007-7.007s-3.141-7.007-7.007-7.007z" />
            </svg>
            <styles.SearchInput
              type="search"
              name="search"
              id="search"
              placeholder="  Search for a stock"
              onChange={handleSearchChange}
            />
          </styles.SearchBar>
        </styles.FilterHeader>
        <styles.DateContainer>
          <styles.DateInput 
            type="date"
            name="start-date"
            id="start-date"
            placeholder="Start Date"
            onChange={handleStartDateChange}
          />
          <styles.DateInput
            type="date"
            name="end-date"
            id="end-date"
            placeholder="End Date"
            onChange={handleEndDateChange}
          />
        </styles.DateContainer>
        <styles.TransactionOutcomeContainer>
          <div>
            <styles.FilterCheckbox 
              type="checkbox" 
              name="passed" 
              id="passed" 
              onChange={() => setPassedChecked(!passedChecked)} 
            />
            <styles.FilterLabel htmlFor="passed"> Passed</styles.FilterLabel>
          </div>
          <div>
            <styles.FilterCheckbox 
              type="checkbox" 
              name="failed" 
              id="failed" 
              onChange={() => setFailedChecked(!failedChecked)} 
            />
            <styles.FilterLabel htmlFor="failed"> Failed</styles.FilterLabel>
          </div>
        </styles.TransactionOutcomeContainer>
        <styles.StocksFilterContainer>
          {uniqueStockNames.map((stock) => (
            <div key={stock}>
              <styles.FilterCheckbox 
                type="checkbox" 
                name={stock} 
                id={stock} 
                onChange={() => handleCheckboxChange(stock)} 
              />
              <styles.FilterLabel htmlFor={stock}> {stock}</styles.FilterLabel>
            </div>
          ))}
        </styles.StocksFilterContainer>
      </styles.FilterContainer>
      <styles.TransactionsContainer>
        {transactionsStatus === 'pending' ? (
          <div style={{ display: 'flex', justifyContent: 'center', height: '100vh' }}>
            <ClipLoader color='#0C359E' loading={true} size={120} aria-label="Loading Spinner" />
          </div>
        ) : (
<styles.TransactionsContainer>
  {Object.entries(groupTransactionsByDate(filteredTransactionsList)).map(([date, transactions]) => (
    <styles.DateWiseContainer key={date}>
      <styles.TransactionsDate>{restructureDate(date)}</styles.TransactionsDate>
      <div>
        {transactions.map((transaction: ITransaction) => (
          <styles.Transaction 
            key={transaction.timestamp} 
            filtered={selectedStocks.includes(transaction.stock_name)}
            color='grey'
          >
            <styles.Info>
              <styles.NameSymbol style={{ color: selectedStocks.includes(transaction.stock_name) ? 'black' : '' }}>
                <div>{transaction.stock_name}</div>
                <div>{transaction.stock_symbol}</div>
              </styles.NameSymbol>
              <styles.PriceTime>
                <div>{transaction.transaction_price}</div>
                <div>{restructureTime(transaction.timestamp)}</div>
              </styles.PriceTime>
            </styles.Info>
            <styles.Outcome>
              {transaction.status === "Passed" ? (
                <styles.Green 
                  width="15"
                  height="15"
                  viewBox="0 0 24 24"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <circle cx="11.998" cy="11.998" r="9.998" />
                </styles.Green>
              ) : (
                <styles.Red 
                  width="15"
                  height="15"
                  viewBox="0 0 24 24"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <circle cx="11.998" cy="11.998" r="9.998" />
                </styles.Red>
              )}
            </styles.Outcome>
          </styles.Transaction>
        ))}
      </div>
    </styles.DateWiseContainer>
  ))}
</styles.TransactionsContainer>
        )}
      </styles.TransactionsContainer>
    </styles.PortfolioContainer>
  );
}



