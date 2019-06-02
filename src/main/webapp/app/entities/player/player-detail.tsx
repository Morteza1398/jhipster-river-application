import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './player.reducer';
import { IPlayer } from 'app/shared/model/player.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlayerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PlayerDetail extends React.Component<IPlayerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { playerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Player [<b>{playerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="username">Username</span>
            </dt>
            <dd>{playerEntity.username}</dd>
            <dt>
              <span id="state">State</span>
            </dt>
            <dd>{playerEntity.state}</dd>
            <dt>
              <span id="disconnect">Disconnect</span>
            </dt>
            <dd>{playerEntity.disconnect ? 'true' : 'false'}</dd>
            <dt>
              <span id="wantToLeave">Want To Leave</span>
            </dt>
            <dd>{playerEntity.wantToLeave ? 'true' : 'false'}</dd>
            <dt>
              <span id="satOutHands">Sat Out Hands</span>
            </dt>
            <dd>{playerEntity.satOutHands}</dd>
            <dt>
              <span id="seatNumber">Seat Number</span>
            </dt>
            <dd>{playerEntity.seatNumber}</dd>
            <dt>
              <span id="tableRest">Table Rest</span>
            </dt>
            <dd>{playerEntity.tableRest}</dd>
            <dt>
              <span id="handRest">Hand Rest</span>
            </dt>
            <dd>{playerEntity.handRest}</dd>
            <dt>
              <span id="chipInStage">Chip In Stage</span>
            </dt>
            <dd>{playerEntity.chipInStage}</dd>
            <dt>
              <span id="cards">Cards</span>
            </dt>
            <dd>{playerEntity.cards}</dd>
            <dt>Table Snapshot</dt>
            <dd>{playerEntity.tableSnapshot ? playerEntity.tableSnapshot.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/player" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/player/${playerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ player }: IRootState) => ({
  playerEntity: player.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlayerDetail);
