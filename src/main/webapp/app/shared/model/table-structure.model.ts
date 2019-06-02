export interface ITableStructure {
  id?: number;
  bigBlind?: number;
  rakePolicy?: string;
  numberOfSeats?: number;
  minBuyIn?: number;
  maxBuyIn?: number;
}

export const defaultValue: Readonly<ITableStructure> = {};
