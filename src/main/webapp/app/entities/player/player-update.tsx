import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';
import { getEntities as getTableSnapshots } from 'app/entities/table-snapshot/table-snapshot.reducer';
import { getEntity, updateEntity, createEntity, reset } from './player.reducer';
import { IPlayer } from 'app/shared/model/player.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlayerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPlayerUpdateState {
  isNew: boolean;
  tableSnapshotId: string;
}

export class PlayerUpdate extends React.Component<IPlayerUpdateProps, IPlayerUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      tableSnapshotId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getTableSnapshots();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { playerEntity } = this.props;
      const entity = {
        ...playerEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/player');
  };

  render() {
    const { playerEntity, tableSnapshots, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jRiverApplicationApp.player.home.createOrEditLabel">Create or edit a Player</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : playerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="player-id">ID</Label>
                    <AvInput id="player-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="usernameLabel" for="player-username">
                    Username
                  </Label>
                  <AvField id="player-username" type="text" name="username" />
                </AvGroup>
                <AvGroup>
                  <Label id="stateLabel" for="player-state">
                    State
                  </Label>
                  <AvInput
                    id="player-state"
                    type="select"
                    className="form-control"
                    name="state"
                    value={(!isNew && playerEntity.state) || 'EMPTY'}
                  >
                    <option value="EMPTY">EMPTY</option>
                    <option value="RESERVE">RESERVE</option>
                    <option value="WAIT_FOR_BB">WAIT_FOR_BB</option>
                    <option value="WANT_TO_PAY_BB">WANT_TO_PAY_BB</option>
                    <option value="NORMAL">NORMAL</option>
                    <option value="ALL_IN">ALL_IN</option>
                    <option value="FOLDED">FOLDED</option>
                    <option value="CAN_MOCK">CAN_MOCK</option>
                    <option value="WAIT_FOR_LEAVE">WAIT_FOR_LEAVE</option>
                    <option value="CAN_REBUY">CAN_REBUY</option>
                    <option value="SITOUT">SITOUT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="disconnectLabel" check>
                    <AvInput id="player-disconnect" type="checkbox" className="form-control" name="disconnect" />
                    Disconnect
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="wantToLeaveLabel" check>
                    <AvInput id="player-wantToLeave" type="checkbox" className="form-control" name="wantToLeave" />
                    Want To Leave
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="satOutHandsLabel" for="player-satOutHands">
                    Sat Out Hands
                  </Label>
                  <AvField id="player-satOutHands" type="string" className="form-control" name="satOutHands" />
                </AvGroup>
                <AvGroup>
                  <Label id="seatNumberLabel" for="player-seatNumber">
                    Seat Number
                  </Label>
                  <AvField id="player-seatNumber" type="string" className="form-control" name="seatNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="tableRestLabel" for="player-tableRest">
                    Table Rest
                  </Label>
                  <AvField id="player-tableRest" type="string" className="form-control" name="tableRest" />
                </AvGroup>
                <AvGroup>
                  <Label id="handRestLabel" for="player-handRest">
                    Hand Rest
                  </Label>
                  <AvField id="player-handRest" type="string" className="form-control" name="handRest" />
                </AvGroup>
                <AvGroup>
                  <Label id="chipInStageLabel" for="player-chipInStage">
                    Chip In Stage
                  </Label>
                  <AvField id="player-chipInStage" type="string" className="form-control" name="chipInStage" />
                </AvGroup>
                <AvGroup>
                  <Label id="cardsLabel" for="player-cards">
                    Cards
                  </Label>
                  <AvField id="player-cards" type="text" name="cards" />
                </AvGroup>
                <AvGroup>
                  <Label for="player-tableSnapshot">Table Snapshot</Label>
                  <AvInput id="player-tableSnapshot" type="select" className="form-control" name="tableSnapshot.id">
                    <option value="" key="0" />
                    {tableSnapshots
                      ? tableSnapshots.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/player" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  tableSnapshots: storeState.tableSnapshot.entities,
  playerEntity: storeState.player.entity,
  loading: storeState.player.loading,
  updating: storeState.player.updating,
  updateSuccess: storeState.player.updateSuccess
});

const mapDispatchToProps = {
  getTableSnapshots,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlayerUpdate);
