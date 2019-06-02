import { IPot } from 'app/shared/model/pot.model';
import { IPlayer } from 'app/shared/model/player.model';
import { ITableStructure } from 'app/shared/model/table-structure.model';

export const enum Stage {
  EMPTY = 'EMPTY',
  NOT_STARTED = 'NOT_STARTED',
  PRE_FLOP = 'PRE_FLOP',
  FLOP = 'FLOP',
  TURN = 'TURN',
  RIVER = 'RIVER',
  COLLECT = 'COLLECT',
  MOCK = 'MOCK',
  FINISHED = 'FINISHED'
}

export interface ITableSnapshot {
  id?: number;
  dealerSeatNumber?: number;
  stage?: Stage;
  maxRaisedChip?: number;
  turnActSeatNumber?: number;
  lastToActSeatNumber?: number;
  fullRaiserSeatNumber?: number;
  fullRaiserChipDifference?: number;
  normalPlayersCount?: number;
  chipInStagePattern?: string;
  cards?: string;
  pots?: IPot[];
  players?: IPlayer[];
  structure?: ITableStructure;
}

export const defaultValue: Readonly<ITableSnapshot> = {};
