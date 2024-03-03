import { createAsyncThunk } from "@reduxjs/toolkit";

export const getDetails = createAsyncThunk(
  "rooms/fetchDetails",
  async () => {
    try {
      const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json");
      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error("Failed to fetch stocks data");
    }
  }
);
