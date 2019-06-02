import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './table-snapshot.reducer';
import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITableSnapshotDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TableSnapshotDetail extends React.Component<ITableSnapshotDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tableSnapshotEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TableSnapshot [<b>{tableSnapshotEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dealerSeatNumber">Dealer Seat Number</span>
            </dt>
            <dd>{tableSnapshotEntity.dealerSeatNumber}</dd>
            <dt>
              <span id="stage">Stage</span>
            </dt>
            <dd>{tableSnapshotEntity.stage}</dd>
            <dt>
              <span id="maxRaisedChip">Max Raised Chip</span>
            </dt>
            <dd>{tableSnapshotEntity.maxRaisedChip}</dd>
            <dt>
              <span id="turnActSeatNumber">Turn Act Seat Number</span>
            </dt>
            <dd>{tableSnapshotEntity.turnActSeatNumber}</dd>
            <dt>
              <span id="lastToActSeatNumber">Last To Act Seat Number</span>
            </dt>
            <dd>{tableSnapshotEntity.lastToActSeatNumber}</dd>
            <dt>
              <span id="fullRaiserSeatNumber">Full Raiser Seat Number</span>
            </dt>
            <dd>{tableSnapshotEntity.fullRaiserSeatNumber}</dd>
            <dt>
              <span id="fullRaiserChipDifference">Full Raiser Chip Difference</span>
            </dt>
            <dd>{tableSnapshotEntity.fullRaiserChipDifference}</dd>
            <dt>
              <span id="normalPlayersCount">Normal Players Count</span>
            </dt>
            <dd>{tableSnapshotEntity.normalPlayersCount}</dd>
            <dt>
              <span id="chipInStagePattern">Chip In Stage Pattern</span>
            </dt>
            <dd>{tableSnapshotEntity.chipInStagePattern}</dd>
            <dt>
              <span id="cards">Cards</span>
            </dt>
            <dd>{tableSnapshotEntity.cards}</dd>
            <dt>Structure</dt>
            <dd>{tableSnapshotEntity.structure ? tableSnapshotEntity.structure.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/table-snapshot" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/table-snapshot/${tableSnapshotEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ tableSnapshot }: IRootState) => ({
  tableSnapshotEntity: tableSnapshot.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableSnapshotDetail);
