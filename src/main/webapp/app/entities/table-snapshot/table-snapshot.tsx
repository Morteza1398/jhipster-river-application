import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './table-snapshot.reducer';
import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITableSnapshotProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TableSnapshot extends React.Component<ITableSnapshotProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { tableSnapshotList, match } = this.props;
    return (
      <div>
        <h2 id="table-snapshot-heading">
          Table Snapshots
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Table Snapshot
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Dealer Seat Number</th>
                <th>Stage</th>
                <th>Max Raised Chip</th>
                <th>Turn Act Seat Number</th>
                <th>Last To Act Seat Number</th>
                <th>Full Raiser Seat Number</th>
                <th>Full Raiser Chip Difference</th>
                <th>Normal Players Count</th>
                <th>Chip In Stage Pattern</th>
                <th>Cards</th>
                <th>Structure</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tableSnapshotList.map((tableSnapshot, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tableSnapshot.id}`} color="link" size="sm">
                      {tableSnapshot.id}
                    </Button>
                  </td>
                  <td>{tableSnapshot.dealerSeatNumber}</td>
                  <td>{tableSnapshot.stage}</td>
                  <td>{tableSnapshot.maxRaisedChip}</td>
                  <td>{tableSnapshot.turnActSeatNumber}</td>
                  <td>{tableSnapshot.lastToActSeatNumber}</td>
                  <td>{tableSnapshot.fullRaiserSeatNumber}</td>
                  <td>{tableSnapshot.fullRaiserChipDifference}</td>
                  <td>{tableSnapshot.normalPlayersCount}</td>
                  <td>{tableSnapshot.chipInStagePattern}</td>
                  <td>{tableSnapshot.cards}</td>
                  <td>
                    {tableSnapshot.structure ? (
                      <Link to={`table-structure/${tableSnapshot.structure.id}`}>{tableSnapshot.structure.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tableSnapshot.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tableSnapshot.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tableSnapshot.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ tableSnapshot }: IRootState) => ({
  tableSnapshotList: tableSnapshot.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableSnapshot);
