import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getDetails } from '../Redux/thunks/getDetails';
import * as styles from './componentStyles/content';
import { AppDispatch, RootState } from "../Redux/stores/Store";
import { RoomItem } from '../Redux/slices/roomSlice';

export function Content() {
  const dispatch: AppDispatch = useDispatch();
  const { roomTypes, loading, error } = useSelector((state: RootState) => state.hotel);
  const [selectedRoomType, setSelectedRoomType] = useState<RoomItem | null>(null);
  const [selectedAddOns, setSelectedAddOns] = useState<string[]>([]);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [bill, setBill] = useState('');

  useEffect(() => {
    dispatch(getDetails());
  }, [dispatch]);

  useEffect(() => {
    // Updating the total cost when any selection changes
    if (isValidSelection()) {
      setBill(calculateTotalCost());
    }
  }, [selectedRoomType, selectedAddOns, startDate, endDate]);

  // Adjusting the  start date and end date 
  useEffect(() => {
    if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
      setEndDate('');
    }
  }, [startDate, endDate]);

  const getButtonStyle = (room: RoomItem) => ({
    ...styles.roomBtn,
    border: selectedRoomType && selectedRoomType.id === room.id ? '2px solid #f08a5d' : '1px solid #ccc',
    color: selectedRoomType && selectedRoomType.id === room.id ? '#f08a5d' : '#000',
  });

  const handleAddOnSelection = (addOnName: string) => {
    setSelectedAddOns(prev => prev.includes(addOnName) ? prev.filter(name => name !== addOnName) : [...prev, addOnName]);
  };

  const isValidSelection = () => {
    return selectedRoomType && startDate && endDate && new Date(startDate) < new Date(endDate);
  };

  const calculateDays = () => {
    const start = new Date(startDate);
    const end = new Date(endDate);
    return Math.max((end - start) / (1000 * 60 * 60 * 24), 1);
  };

  const calculateTotalCost = () => {
    if (!selectedRoomType || !startDate || !endDate) return '0';
    const days = calculateDays();
    const roomCost = days * selectedRoomType.costPerNight;
    const addOnsCost = selectedRoomType.addOns.filter(addOn => selectedAddOns.includes(addOn.name)).reduce((total, addOn) => total + addOn.cost * days, 0);
    const totalCost = roomCost + addOnsCost;
    const tax = totalCost * 0.18; // 18% tax
    return new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' }).format(totalCost + tax);
  };

  const handleSubmit = () => {
    console.log('Selected Room Type:', selectedRoomType?.name);
    console.log('Start Date:', startDate);
    console.log('End Date:', endDate);
    console.log('Selected Add-Ons:', selectedAddOns.join(', '));
    console.log(`Total Cost: ${bill}`);
  };

  return (
    <main>
      <div style={styles.Header}>Hotel Booking</div>
      <div style={styles.titleBar}>
        <span style={styles.roomTitle}>Select Room Type</span>
      </div>
      <div style={styles.roomItems}>
        {loading ? <p>Loading...</p> : error ? <p>Error: {error}</p> : roomTypes.map(room => (
          <button key={room.id} style={getButtonStyle(room)} onClick={() => setSelectedRoomType(room)}>{room.name}</button>
        ))}
      </div>

      <div style={styles.titleBar}>
        <span style={styles.roomTitle}>Select Date</span>
      </div>
      <div style={styles.dateItems}>
        <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} style={styles.date} />
        <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} style={styles.date} disabled={!startDate} min={startDate && new Date(new Date(startDate).getTime() + (24 * 60 * 60 * 1000)).toISOString().split('T')[0]} />
      </div> 

       <div style={styles.titleBar}>
        <span style={styles.roomTitle}>Select additional add ons / preferences</span>
      </div>
      {selectedRoomType ? (
        <div style={styles.addOnItems}>
          {selectedRoomType.addOns.map(addOn => (
            <button
              key={addOn.name}
              style={selectedAddOns.includes(addOn.name) ? { ...styles.addOnBtn, border:'2px solid #f08a5d ',color:'#f08a5d' } : styles.addOnBtn}
              onClick={() => handleAddOnSelection(addOn.name)}
            >
              {addOn.name}
            </button>
          ))}
        </div>
      ) : (
        <div>Please select a room type to see add-ons.</div>
      )}
      <div style={{...styles.cost, opacity: isValidSelection() ? 1 : 0}}> 
        <span>Cost + 18% GST = {bill}</span>
      </div>
      <div style={styles.submit}>
        <button
          disabled={!isValidSelection()}
          onClick={handleSubmit}
          style={{ ...styles.submitBtn, opacity: isValidSelection() ? 1 : 0.5 }}
        >
          Submit
        </button>
      </div>
    </main>
  );
}
